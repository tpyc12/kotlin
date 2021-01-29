package com.example.myproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myproject.model.Note
import com.example.myproject.model.NoteResult
import com.example.myproject.model.NoteResult.Error
import com.example.myproject.model.NoteResult.Success
import com.example.myproject.model.Repository
import com.example.myproject.ui.BaseViewModel
import com.example.myproject.ui.MainViewState

class MainViewModel(val repository: Repository = Repository) :
        BaseViewModel<List<Note>?, MainViewState>() {

    private val notesObserver = object : Observer<NoteResult> {
        override fun onChanged(t: NoteResult?) {
            if (t == null) return
            when (t) {
                is Success<*> -> {
                    viewStateLiveData.value = MainViewState(notes = t.data as? List<Note>)
                }
                is Error -> {
                    viewStateLiveData.value = MainViewState(error = t.error)
                }
            }
        }
    }

    private val repositoryNotes = repository.getNotes()

    init {
        viewStateLiveData.value = MainViewState()
        repositoryNotes.observeForever(notesObserver)
    }

    override fun onCleared() {
        repositoryNotes.removeObserver(notesObserver)
    }
}