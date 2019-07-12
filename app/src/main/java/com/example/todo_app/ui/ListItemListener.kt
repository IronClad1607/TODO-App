package com.example.todo_app.ui

import com.example.todo_app.database.Todo

interface ListItemListener {
    fun checkClicked(task: Todo, position: Int)

    fun deleteClicked(task: Todo,position: Int)
}