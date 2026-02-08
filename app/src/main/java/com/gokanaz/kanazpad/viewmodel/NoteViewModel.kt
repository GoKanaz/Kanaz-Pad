package com.gokanaz.kanazpad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gokanaz.kanazpad.data.model.Folder
import com.gokanaz.kanazpad.data.model.Note
import com.gokanaz.kanazpad.data.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val allNotes: LiveData<List<Note>> = repository.getAllNotes()
    val allFolders: LiveData<List<Folder>> = repository.getAllFolders()

    fun insertNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun getNoteById(id: Long): LiveData<Note?> {
        return repository.getNoteById(id)
    }

    fun insertFolder(folder: Folder) = viewModelScope.launch {
        repository.insertFolder(folder)
    }

    fun updateFolder(folder: Folder) = viewModelScope.launch {
        repository.updateFolder(folder)
    }

    fun deleteFolder(folder: Folder) = viewModelScope.launch {
        repository.deleteFolder(folder)
    }

    fun getNotesByFolder(folderId: Long): LiveData<List<Note>> {
        return repository.getNotesByFolder(folderId)
    }

    fun searchNotes(query: String): LiveData<List<Note>> {
        return repository.searchNotes(query)
    }
}
