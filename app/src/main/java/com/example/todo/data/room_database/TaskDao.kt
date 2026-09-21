package com.example.todo.data.room_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskItem) //database opration bg me chale is liye suspend fun

    @Update
    suspend fun upadate(task: TaskItem)

    @Delete
    suspend fun delete(task: TaskItem)

    //manuall query for data
    @Query("SELECT * FROM tasks ORDER BY id DESC") //Database, mujhe ye data do."
    fun getAllTasks(): Flow<List<TaskItem>> //Aur jab data change ho, mujhe new data bhi dete rehna.

}