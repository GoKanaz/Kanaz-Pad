package com.gokanaz.kanazpad.utils

import android.content.Context
import android.net.Uri
import com.gokanaz.kanazpad.data.model.Note
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Date

object FileImporter {
    
    fun importFromUri(context: Context, uri: Uri): Note? {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            inputStream?.use { stream ->
                val reader = BufferedReader(InputStreamReader(stream))
                val content = reader.readText()
                
                // Get file name from URI
                val fileName = getFileName(context, uri)
                val title = fileName?.replace(".md", "")?.replace(".txt", "") 
                    ?: "Imported Note"
                
                return Note(
                    title = title,
                    content = content,
                    createdAt = Date(),
                    updatedAt = Date()
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
    
    private fun getFileName(context: Context, uri: Uri): String? {
        var fileName: String? = null
        
        // Try to get file name from URI
        context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val nameIndex = cursor.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
                if (nameIndex >= 0) {
                    fileName = cursor.getString(nameIndex)
                }
            }
        }
        
        // Fallback to last path segment
        if (fileName == null) {
            fileName = uri.lastPathSegment
        }
        
        return fileName
    }
    
    fun validateFileType(context: Context, uri: Uri): Boolean {
        val fileName = getFileName(context, uri)
        return fileName?.endsWith(".md") == true || 
               fileName?.endsWith(".txt") == true ||
               fileName?.endsWith(".markdown") == true
    }
}
