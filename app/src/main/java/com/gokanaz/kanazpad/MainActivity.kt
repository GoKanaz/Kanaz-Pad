package com.gokanaz.kanazpad

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gokanaz.kanazpad.data.model.Note
import com.gokanaz.kanazpad.ui.editor.EditorActivity
import com.gokanaz.kanazpad.utils.FileImporter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabNewNote: FloatingActionButton
    private lateinit var adapter: NotesAdapter
    
    private val repository by lazy {
        (application as KanazPadApplication).repository
    }
    
    private val importFileLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            if (FileImporter.validateFileType(this, it)) {
                val note = FileImporter.importFromUri(this, it)
                note?.let { importedNote ->
                    lifecycleScope.launch {
                        repository.insertNote(importedNote)
                        Toast.makeText(
                            this@MainActivity,
                            "File imported successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    this,
                    "Please select a .md or .txt file",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = "Kanaz Pad"
        supportActionBar?.subtitle = "@GoKanaz"
        
        recyclerView = findViewById(R.id.recyclerViewNotes)
        fabNewNote = findViewById(R.id.fabNewNote)
        
        setupRecyclerView()
        setupFab()
        observeNotes()
        
        handleIntent(intent)
    }
    
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { handleIntent(it) }
    }
    
    private fun handleIntent(intent: Intent) {
        if (intent.action == Intent.ACTION_VIEW) {
            intent.data?.let { uri ->
                val note = FileImporter.importFromUri(this, uri)
                note?.let {
                    lifecycleScope.launch {
                        val noteId = repository.insertNote(it)
                        openEditor(noteId)
                    }
                }
            }
        }
    }
    
    private fun setupRecyclerView() {
        adapter = NotesAdapter(
            onNoteClick = { note ->
                openEditor(note.id)
            },
            onNoteLongClick = { note ->
                showNoteOptionsDialog(note)
            }
        )
        
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    
    private fun setupFab() {
        fabNewNote.setOnClickListener {
            createNewNote()
        }
    }
    
    private fun observeNotes() {
        lifecycleScope.launch {
            repository.getAllNotes().collectLatest { notes ->
                adapter.submitList(notes)
            }
        }
    }
    
    private fun createNewNote() {
        lifecycleScope.launch {
            val newNote = Note(
                title = "New Note",
                content = "# New Note\n\nStart writing here...",
                createdAt = Date(),
                updatedAt = Date()
            )
            val noteId = repository.insertNote(newNote)
            openEditor(noteId)
        }
    }
    
    private fun openEditor(noteId: Long) {
        val intent = Intent(this, EditorActivity::class.java)
        intent.putExtra("NOTE_ID", noteId)
        startActivity(intent)
    }
    
    private fun showNoteOptionsDialog(note: Note) {
        val options = arrayOf("Delete", "Favorite", "Pin", "Export")
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle(note.title)
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> deleteNote(note)
                1 -> toggleFavorite(note)
                2 -> togglePin(note)
                3 -> showExportOptions(note)
            }
        }
        builder.show()
    }
    
    private fun deleteNote(note: Note) {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Delete Note")
            .setMessage("Are you sure you want to delete \"${note.title}\"?")
            .setPositiveButton("Delete") { _, _ ->
                lifecycleScope.launch {
                    repository.deleteNote(note)
                    Toast.makeText(
                        this@MainActivity,
                        "Note deleted",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    
    private fun toggleFavorite(note: Note) {
        lifecycleScope.launch {
            repository.updateNote(note.copy(isFavorite = !note.isFavorite))
        }
    }
    
    private fun togglePin(note: Note) {
        lifecycleScope.launch {
            repository.updateNote(note.copy(isPinned = !note.isPinned))
        }
    }
    
    private fun showExportOptions(note: Note) {
        val options = arrayOf("Export to PDF", "Export to HTML", "Export to DOCX")
        val builder = androidx.appcompat.app.AlertDialog.Builder(this)
        builder.setTitle("Export ${note.title}")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> exportToPdf(note)
                1 -> exportToHtml(note)
                2 -> exportToDocx(note)
            }
        }
        builder.show()
    }
    
    private fun exportToPdf(note: Note) {
        lifecycleScope.launch {
            try {
                val file = com.gokanaz.kanazpad.utils.PdfExporter.exportNoteToPdf(this@MainActivity, note)
                Toast.makeText(
                    this@MainActivity,
                    "Exported to ${file.absolutePath}",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Export failed: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    
    private fun exportToHtml(note: Note) {
        lifecycleScope.launch {
            try {
                val file = com.gokanaz.kanazpad.utils.HtmlExporter.exportNoteToHtml(this@MainActivity, note)
                Toast.makeText(
                    this@MainActivity,
                    "Exported to ${file.absolutePath}",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Export failed: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    
    private fun exportToDocx(note: Note) {
        lifecycleScope.launch {
            try {
                val file = com.gokanaz.kanazpad.utils.DocxExporter.exportNoteToDocx(this@MainActivity, note)
                Toast.makeText(
                    this@MainActivity,
                    "Exported to ${file.absolutePath}",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Export failed: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    observeNotes()
                } else {
                    searchNotes(newText)
                }
                return true
            }
        })
        
        return true
    }
    
    private fun searchNotes(query: String) {
        lifecycleScope.launch {
            repository.searchNotes(query).collectLatest { notes ->
                adapter.submitList(notes)
            }
        }
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Settings coming soon", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
