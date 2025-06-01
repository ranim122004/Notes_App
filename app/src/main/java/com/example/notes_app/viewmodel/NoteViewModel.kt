package com.example.notes_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes_app.data.Note
import com.example.notes_app.data.NoteDatabase
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = NoteDatabase.getDatabase(application).noteDao()
    val allNotes: LiveData<List<Note>> = dao.getAllNotes()

    fun insert(note: Note) = viewModelScope.launch {
        dao.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        dao.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        dao.delete(note)
    }
}
