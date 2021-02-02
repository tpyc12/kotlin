package com.example.myproject.model.providers

import androidx.lifecycle.LiveData
import com.example.myproject.model.Note
import com.example.myproject.model.NoteResult
import com.example.myproject.model.User

interface RemoteDataProvider {

    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>
}