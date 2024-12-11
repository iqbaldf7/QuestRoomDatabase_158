package com.example.activity7.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.activity7.KrsApp

object PenyediaViewModel {
    val  Factory = viewModelFactory {
        initializer {
            MahasiswaViewModel(
                KrsApp().containerApp.repositoryMhs
            )
        }
    }
}


fun CreationExtras.KrsApp(): KrsApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KrsApp)