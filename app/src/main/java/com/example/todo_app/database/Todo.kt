package com.example.todo_app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val task : String,

    @ColumnInfo(name = "isDone")
    val status : Boolean
)