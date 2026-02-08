package com.gokanaz.kanazpad.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "folders")
data class Folder(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val name: String,
    
    val parentFolderId: Long? = null,
    
    val createdAt: Date = Date(),
    
    val color: String? = null,
    
    val icon: String? = null
)
