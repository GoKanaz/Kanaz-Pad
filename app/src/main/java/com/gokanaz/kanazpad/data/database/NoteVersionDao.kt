package com.gokanaz.kanazpad.data.database

import androidx.room.*
import com.gokanaz.kanazpad.data.model.NoteVersion
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteVersionDao {
    @Query("SELECT * FROM note_versions WHERE noteId = :noteId ORDER BY createdAt DESC")
    fun getVersionsByNoteId(noteId: Long): Flow<List<NoteVersion>>
    
    @Query("SELECT * FROM note_versions WHERE noteId = :noteId ORDER BY createdAt DESC LIMIT :limit")
    suspend fun getRecentVersions(noteId: Long, limit: Int = 10): List<NoteVersion>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVersion(version: NoteVersion): Long
    
    @Delete
    suspend fun deleteVersion(version: NoteVersion)
    
    @Query("DELETE FROM note_versions WHERE noteId = :noteId")
    suspend fun deleteVersionsByNoteId(noteId: Long)
    
    @Query("""
        DELETE FROM note_versions 
        WHERE noteId = :noteId 
        AND id NOT IN (
            SELECT id FROM note_versions 
            WHERE noteId = :noteId 
            ORDER BY createdAt DESC 
            LIMIT :keepCount
        )
    """)
    suspend fun deleteOldVersions(noteId: Long, keepCount: Int = 10)
}
