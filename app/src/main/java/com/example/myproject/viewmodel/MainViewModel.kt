package com.example.myproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myproject.model.Repository
import com.example.myproject.ui.MainViewState

class MainViewModel : ViewModel() {

    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = MainViewState(Repository.getNotes())
    }

    fun viewState(): LiveData<MainViewState> = viewStateLiveData
}