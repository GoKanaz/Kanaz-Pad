package com.gokanaz.kanazpad.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gokanaz.kanazpad.R
import com.gokanaz.kanazpad.data.model.Note
import java.text.SimpleDateFormat
import java.util.*

class NoteAdapter(
    private val onNoteClick: (Note) -> Unit,
    private val onMenuClick: (Note, View) -> Unit
) : ListAdapter<Note, NoteAdapter.NoteViewHolder>(NoteDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvContent: TextView = itemView.findViewById(R.id.tvContent)
        private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        private val tvFolder: TextView = itemView.findViewById(R.id.tvFolder)
        private val ivMenu: ImageView = itemView.findViewById(R.id.ivMenu)

        fun bind(note: Note) {
            tvTitle.text = note.title
            tvContent.text = note.content
            
            val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
            tvDate.text = dateFormat.format(note.updatedAt)
            
            tvFolder.text = note.folderId?.toString() ?: ""
            tvFolder.visibility = if (note.folderId != null) View.VISIBLE else View.GONE

            itemView.setOnClickListener { onNoteClick(note) }
            ivMenu.setOnClickListener { onMenuClick(note, it) }
        }
    }

    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}
