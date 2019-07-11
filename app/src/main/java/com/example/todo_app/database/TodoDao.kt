package com.example.todo_app.database

import androidx.room.Insert
import androidx.room.Query

interface TodoDao {

    @Insert
    fun insertRow(todo: Todo)

    @Query("SELECT * FROM todo")
    fun getAllRows() : List<Todo>

}