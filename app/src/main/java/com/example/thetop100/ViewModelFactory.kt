package com.example.thetop100

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository:RepoDataSource, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(RepoListViewModel::class.java) -> RepoListViewModel(repository,application)
                else -> throw IllegalArgumentException("Classe desconhecida")
            }
        } as T

}