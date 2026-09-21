package com.example.todo.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.data.room_database.TaskDatabase
import com.example.todo.data.room_database.TaskItem
import com.example.todo.repository.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskviewModel(application: Application): AndroidViewModel(application) {
    //dao instance
    private val dao = TaskDatabase.getDatabase(application).taskDao()
    //instance for repos
    private val repository = TaskRepository(dao)

    //all task from repo
    val allTasks: StateFlow<List<TaskItem>> = repository.getAllTasks()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun addTask(task: TaskItem){
        viewModelScope.launch {
            repository.insert(task)
        }
    }

    fun upadateTask(task: TaskItem){
        viewModelScope.launch {
            repository.update(task)
        }
    }
    fun deleteTask(task: TaskItem){
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}
