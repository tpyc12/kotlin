package com.example.myproject.viewmodel

import androidx.lifecycle.Observer
import com.example.myproject.model.Note
import com.example.myproject.model.NoteResult
import com.example.myproject.model.NoteResult.Success
import com.example.myproject.model.NoteResult.Error
import com.example.myproject.model.Repository
import com.example.myproject.ui.states.NoteViewState

class NoteViewModel(val repository: Repository) :
    BaseViewModel<NoteViewState.Data, NoteViewState>() {

    private val currentNote: Note?
        get() = viewStateLiveData.value?.data?.note

    fun saveChanges(note: Note) {
        viewStateLiveData.value = NoteViewState(NoteViewState.Data(note = note))
    }

    override fun onCleared() {
        currentNote?.let { repository.saveNote(it) }
    }

    fun loadNote(noteId: String) {
        repository.getNoteById(noteId).observeForever { t ->
            t?.let { noteResult ->
                viewStateLiveData.value = when (noteResult) {
                    is Success<*> ->
                        NoteViewState(NoteViewState.Data(note = noteResult.data as? Note))
                    is Error ->
                        NoteViewState(error = noteResult.error)
                }
            }
        }
    }

    fun deleteNote() {
        currentNote?.let {
            repository.deleteNote(it.id).observeForever { result ->
                result?.let { noteResult ->
                    viewStateLiveData.value = when (noteResult) {
                        is Success<*> ->
                            NoteViewState(NoteViewState.Data(isDeleted = true))
                        is Error ->
                            NoteViewState(error = noteResult.error)
                    }
                }
            }
        }
    }
}
