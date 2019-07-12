package com.example.todo_app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {

    @Insert
    fun insertRow(todo: Todo)

    @Query("SELECT * FROM todo")
    fun getAllRows() : List<Todo>

}