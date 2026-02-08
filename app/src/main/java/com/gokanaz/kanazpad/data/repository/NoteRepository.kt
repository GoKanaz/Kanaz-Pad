package com.gokanaz.kanazpad.data.repository

import com.gokanaz.kanazpad.data.database.NoteDao
import com.gokanaz.kanazpad.data.database.NoteVersionDao
import com.gokanaz.kanazpad.data.model.Note
import com.gokanaz.kanazpad.data.model.NoteVersion
import kotlinx.coroutines.flow.Flow
import java.util.Date

class NoteRepository(
    private val noteDao: NoteDao,
    private val versionDao: NoteVersionDao
) {
    
    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()
    
    fun getNotesByFolder(folderId: Long): Flow<List<Note>> = 
        noteDao.getNotesByFolder(folderId)
    
    fun searchNotes(query: String): Flow<List<Note>> = 
        noteDao.searchNotes(query)
    
    fun getFavoriteNotes(): Flow<List<Note>> = noteDao.getFavoriteNotes()
    
    suspend fun getNoteById(noteId: Long): Note? = noteDao.getNoteById(noteId)
    
    suspend fun getAllTags(): List<String> {
        val tagLists = noteDao.getAllTags()
        return tagLists.flatten().distinct().sorted()
    }
    
    suspend fun insertNote(note: Note): Long {
        return noteDao.insertNote(note)
    }
    
    suspend fun updateNote(note: Note) {
        noteDao.updateNote(note.copy(updatedAt = Date()))
    }
    
    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }
    
    suspend fun deleteNoteById(noteId: Long) {
        noteDao.deleteNoteById(noteId)
    }
    
    // Version History
    fun getVersionHistory(noteId: Long): Flow<List<NoteVersion>> = 
        versionDao.getVersionsByNoteId(noteId)
    
    suspend fun saveVersion(note: Note, comment: String? = null) {
        val version = NoteVersion(
            noteId = note.id,
            content = note.content,
            title = note.title,
            comment = comment
        )
        versionDao.insertVersion(version)
        // Keep only last 10 versions
        versionDao.deleteOldVersions(note.id, 10)
    }
    
    suspend fun restoreVersion(noteId: Long, version: NoteVersion) {
        val note = getNoteById(noteId)
        note?.let {
            updateNote(
                it.copy(
                    content = version.content,
                    title = version.title,
                    updatedAt = Date()
                )
            )
        }
    }
    
    // Auto-save functionality
    suspend fun autoSave(note: Note) {
        val existingNote = getNoteById(note.id)
        existingNote?.let {
            // Only save if content changed
            if (it.content != note.content) {
                updateNote(note)
            }
        } ?: insertNote(note)
    }
}
