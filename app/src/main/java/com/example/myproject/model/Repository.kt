package com.example.myproject.model

import com.example.myproject.model.providers.FireStoreProvider
import com.example.myproject.model.providers.RemoteDataProvider

class Repository(private val remoteDataProvider: RemoteDataProvider) {

    fun getNotes() = remoteDataProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteDataProvider.saveNote(note)
    fun getNoteById(id: String) = remoteDataProvider.getNoteById(id)
    fun getCurrentUser() = remoteDataProvider.getCurrentUser()
    fun deleteNote(noteId: String) = remoteDataProvider.deleteNote(noteId)
}
