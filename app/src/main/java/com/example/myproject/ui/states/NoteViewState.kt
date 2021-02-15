package com.example.myproject.ui.states

import com.example.myproject.model.Note

class NoteViewState(data: Data = Data(), error: Throwable? = null) :
        BaseViewState<NoteViewState.Data>(data, error){

        data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}