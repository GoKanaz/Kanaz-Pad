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
import com.gokanaz.kanazpad.data.model.Folder

class FolderAdapter(
    private val onFolderClick: (Folder) -> Unit,
    private val onMenuClick: (Folder, View) -> Unit
) : ListAdapter<Folder, FolderAdapter.FolderViewHolder>(FolderDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_folder, parent, false)
        return FolderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvFolderName: TextView = itemView.findViewById(R.id.tvFolderName)
        private val tvNoteCount: TextView = itemView.findViewById(R.id.tvNoteCount)
        private val ivMenu: ImageView = itemView.findViewById(R.id.ivMenu)

        fun bind(folder: Folder) {
            tvFolderName.text = folder.name
            tvNoteCount.text = "Folder"

            itemView.setOnClickListener { onFolderClick(folder) }
            ivMenu.setOnClickListener { onMenuClick(folder, it) }
        }
    }

    class FolderDiffCallback : DiffUtil.ItemCallback<Folder>() {
        override fun areItemsTheSame(oldItem: Folder, newItem: Folder): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Folder, newItem: Folder): Boolean {
            return oldItem == newItem
        }
    }
}
