package com.example.easynote.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.easynote.Database.NoteDatabase
import com.example.easynote.Database.NotesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteViewModel(application: Application) : AndroidViewModel(application){

    private val repository: NotesRepo

    val AllNotes : LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getDao()
        repository = NotesRepo(dao)
        AllNotes = repository.allNotes
    }


    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
    fun update(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }
}