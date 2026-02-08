package com.gokanaz.kanazpad.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.gokanaz.kanazpad.data.database.Converters
import java.util.Date

@Entity(tableName = "notes")
@TypeConverters(Converters::class)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val title: String,
    
    val content: String,
    
    val createdAt: Date = Date(),
    
    val updatedAt: Date = Date(),
    
    val folderId: Long? = null,
    
    val tags: List<String> = emptyList(),
    
    val isFavorite: Boolean = false,
    
    val isPinned: Boolean = false,
    
    val color: String? = null,
    
    // For attachments - stored as JSON string
    val attachments: String = "[]"
) {
    fun getWordCount(): Int {
        return content.split("\\s+".toRegex()).count { it.isNotBlank() }
    }
    
    fun getCharCount(): Int {
        return content.length
    }
    
    fun getReadingTime(): Int {
        // Assuming 200 words per minute
        return kotlin.math.ceil(getWordCount() / 200.0).toInt()
    }
    
    fun getPreview(maxLength: Int = 100): String {
        return if (content.length > maxLength) {
            content.substring(0, maxLength) + "..."
        } else {
            content
        }
    }
}
