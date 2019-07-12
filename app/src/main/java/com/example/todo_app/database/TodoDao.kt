package com.example.todo_app.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Insert
    fun insertRow(todo: Todo)

    @Query("SELECT * FROM todo")
    fun getAllRows(): List<Todo>

    @Query("UPDATE todo SET isDone = 1 WHERE id = :id")
    fun updateTaskTrue(id: Long?)

    @Query("UPDATE todo SET isDone = 0 WHERE id = :id")
    fun updateTaskFalse(id: Long?)

    @Delete
    fun delete(todo: Todo)
}