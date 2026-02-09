package com.gokanaz.kanazpad.data.repository

import com.gokanaz.kanazpad.data.database.NoteDao
import com.gokanaz.kanazpad.data.database.NoteVersionDao
import com.gokanaz.kanazpad.data.database.FolderDao
import com.gokanaz.kanazpad.data.model.Note
import com.gokanaz.kanazpad.data.model.NoteVersion
import com.gokanaz.kanazpad.data.model.Folder
import kotlinx.coroutines.flow.Flow
import java.util.Date

class NoteRepository(
    private val noteDao: NoteDao,
    private val versionDao: NoteVersionDao,
    private val folderDao: FolderDao
) {
    
    fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes()
    
    fun getNotesByFolder(folderId: Long): Flow<List<Note>> = 
        noteDao.getNotesByFolder(folderId)
    
    fun searchNotes(query: String): Flow<List<Note>> = 
        noteDao.searchNotes(query)
    
    fun getFavoriteNotes(): Flow<List<Note>> = noteDao.getFavoriteNotes()
    
    fun getNoteById(noteId: Long): Flow<Note?> = noteDao.getNoteByIdFlow(noteId)
    
    suspend fun getNoteByIdSuspend(noteId: Long): Note? = noteDao.getNoteById(noteId)
    
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
    
    fun getAllFolders(): Flow<List<Folder>> = folderDao.getAllFolders()
    
    suspend fun insertFolder(folder: Folder): Long {
        return folderDao.insertFolder(folder)
    }
    
    suspend fun updateFolder(folder: Folder) {
        folderDao.updateFolder(folder)
    }
    
    suspend fun deleteFolder(folder: Folder) {
        folderDao.deleteFolder(folder)
    }
    
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
        versionDao.deleteOldVersions(note.id, 10)
    }
    
    suspend fun restoreVersion(noteId: Long, version: NoteVersion) {
        val note = getNoteByIdSuspend(noteId)
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
    
    suspend fun autoSave(note: Note) {
        val existingNote = getNoteByIdSuspend(note.id)
        existingNote?.let {
            if (it.content != note.content) {
                updateNote(note)
            }
        } ?: insertNote(note)
    }
}
