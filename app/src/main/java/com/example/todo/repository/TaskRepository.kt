package com.example.todo.repository

import com.example.todo.data.room_database.TaskDao
import com.example.todo.data.room_database.TaskItem
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val dao: TaskDao) {

    fun getAllTasks(): Flow<List<TaskItem>> {
        return dao.getAllTasks()
    }

    suspend fun insert(task: TaskItem) = dao.insert(task)
    suspend fun update(task: TaskItem) = dao.upadate(task)
    suspend fun delete(task: TaskItem) = dao.delete(task)

}
