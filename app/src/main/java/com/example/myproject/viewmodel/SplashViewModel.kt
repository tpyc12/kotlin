package com.example.myproject.viewmodel

import com.example.myproject.model.NoAuthException
import com.example.myproject.model.Repository
import com.example.myproject.ui.states.SplashViewState

class SplashViewModel(private val repository: Repository = Repository) :
    BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser(){
        repository.getCurrentUser().observeForever { user ->
            viewStateLiveData.value = user?.let {
                SplashViewState(isAuth = true)
            } ?: SplashViewState(error = NoAuthException())
        }
    }
}