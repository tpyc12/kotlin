package com.example.myproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myproject.model.Note
import com.example.myproject.model.Repository

class NoteViewModel(private val repository: Repository = Repository) : ViewModel() {

    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }
}
