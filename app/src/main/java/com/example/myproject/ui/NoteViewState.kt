package com.example.myproject.ui

import com.example.myproject.model.Note

class NoteViewState(note: Note? = null, error: Throwable? = null) :
        BaseViewState<Note?>(note, error)