package com.example.myproject.viewmodel

import androidx.lifecycle.Observer
import com.example.myproject.model.Note
import com.example.myproject.model.NoteResult
import com.example.myproject.model.NoteResult.Success
import com.example.myproject.model.NoteResult.Error
import com.example.myproject.model.Repository
import com.example.myproject.ui.BaseViewModel
import com.example.myproject.ui.NoteViewState

class NoteViewModel(val repository: Repository = Repository) :
BaseViewModel<Note?, NoteViewState>(){

    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        if (pendingNote != null) {
            repository.saveNote(pendingNote!!)
        }
    }

    fun loadNote(noteId: String){
        repository.getNoteById(noteId).observeForever(object : Observer<NoteResult> {
            override fun onChanged(t: NoteResult?) {
                if (t == null) return

                when(t){
                    is Success<*> ->
                        viewStateLiveData.value = NoteViewState(note = t.data as? Note)
                    is Error ->
                        viewStateLiveData.value = NoteViewState(error = t.error)
                }
            }
        })
    }
}
