package com.gokanaz.kanazpad.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "note_versions",
    foreignKeys = [
        ForeignKey(
            entity = Note::class,
            parentColumns = ["id"],
            childColumns = ["noteId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class NoteVersion(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val noteId: Long,
    
    val content: String,
    
    val title: String,
    
    val createdAt: Date = Date(),
    
    val comment: String? = null
)
