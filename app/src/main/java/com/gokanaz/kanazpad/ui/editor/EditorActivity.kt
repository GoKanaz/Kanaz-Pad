package com.gokanaz.kanazpad.ui.editor

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gokanaz.kanazpad.KanazPadApplication
import com.gokanaz.kanazpad.R
import com.gokanaz.kanazpad.data.model.Note
import com.gokanaz.kanazpad.viewmodel.NoteViewModel
import com.gokanaz.kanazpad.viewmodel.NoteViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import java.util.*

class EditorActivity : AppCompatActivity() {

    private lateinit var viewModel: NoteViewModel
    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText
    private lateinit var toolbar: MaterialToolbar
    private var noteId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        val factory = NoteViewModelFactory((application as KanazPadApplication).repository)
        viewModel = ViewModelProvider(this, factory)[NoteViewModel::class.java]

        toolbar = findViewById(R.id.toolbar)
        etTitle = findViewById(R.id.etTitle)
        etContent = findViewById(R.id.etContent)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        noteId = intent.getLongExtra("NOTE_ID", -1)
        if (noteId != -1L) {
            viewModel.getNoteById(noteId).observe(this) { note ->
                note?.let {
                    etTitle.setText(it.title)
                    etContent.setText(it.content)
                }
            }
        }

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_save -> {
                    saveNote()
                    true
                }
                else -> false
            }
        }
    }

    private fun saveNote() {
        val title = etTitle.text.toString().trim()
        val content = etContent.text.toString().trim()

        if (title.isEmpty() && content.isEmpty()) {
            finish()
            return
        }

        val note = if (noteId != -1L) {
            Note(
                id = noteId,
                title = title.ifEmpty { "Untitled" },
                content = content,
                createdAt = Date(),
                updatedAt = Date()
            )
        } else {
            Note(
                title = title.ifEmpty { "Untitled" },
                content = content,
                createdAt = Date(),
                updatedAt = Date()
            )
        }

        if (noteId != -1L) {
            viewModel.updateNote(note)
        } else {
            viewModel.insertNote(note)
        }

        finish()
    }

    override fun onBackPressed() {
        saveNote()
    }
}
