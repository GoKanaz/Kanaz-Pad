package com.gokanaz.kanazpad

import android.app.Application
import com.gokanaz.kanazpad.data.database.AppDatabase
import com.gokanaz.kanazpad.data.repository.NoteRepository

class KanazPadApplication : Application() {
    
    val database by lazy { AppDatabase.getDatabase(this) }
    val noteRepository by lazy {
        NoteRepository(
            database.noteDao(),
            database.noteVersionDao()
        )
    }
    
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    
    companion object {
        lateinit var instance: KanazPadApplication
            private set
    }
}
