package com.example.todo.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class TaskViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    //view model factory ka use  tab karte hai jab viewmodel ke constructor me extra perameter dalna ho
    //this is decide kon sa view model create karna hai

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TaskviewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskviewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}