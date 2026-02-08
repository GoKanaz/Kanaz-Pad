package com.gokanaz.kanazpad.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gokanaz.kanazpad.data.model.Folder
import com.gokanaz.kanazpad.data.model.Note
import com.gokanaz.kanazpad.data.model.NoteVersion

@Database(
    entities = [Note::class, NoteVersion::class, Folder::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun noteDao(): NoteDao
    abstract fun noteVersionDao(): NoteVersionDao
    abstract fun folderDao(): FolderDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "kanaz_pad_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
