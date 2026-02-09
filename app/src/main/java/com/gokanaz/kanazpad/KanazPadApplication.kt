package com.gokanaz.kanazpad

import android.app.Application
import com.gokanaz.kanazpad.data.database.AppDatabase
import com.gokanaz.kanazpad.data.repository.NoteRepository

class KanazPadApplication : Application() {
    
    private val database by lazy { AppDatabase.getDatabase(this) }
    
    val repository by lazy {
        NoteRepository(
            database.noteDao(),
            database.noteVersionDao(),
            database.folderDao()
        )
    }
}
