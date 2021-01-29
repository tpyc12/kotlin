package com.example.myproject.ui

import com.example.myproject.model.Note

class MainViewState(notes: List<Note>? = null, error: Throwable? = null)
    : BaseViewState<List<Note>?>(notes, error)