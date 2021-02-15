package com.example.myproject

import com.example.myproject.model.Repository
import com.example.myproject.model.providers.FireStoreProvider
import com.example.myproject.model.providers.RemoteDataProvider
import com.example.myproject.viewmodel.MainViewModel
import com.example.myproject.viewmodel.NoteViewModel
import com.example.myproject.viewmodel.SplashViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FireStoreProvider(get(), get()) } bind RemoteDataProvider::class
    single { Repository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}
