package com.example.todo_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.todo_app.R
import com.example.todo_app.database.AppDatabase
import com.example.todo_app.database.Todo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "todo.db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

        var list = db.todoDao().getAllRows()
        val todoAdapter = TODOAdapter(list as ArrayList<Todo>)
        todoAdapter.listItemListener = object : ListItemListener {
            override fun checkClicked(task: Todo, position: Int) {
                task.status = !task.status
                if (task.status) {
                    db.todoDao().updateTaskTrue(task.id)
                } else {
                    db.todoDao().updateTaskFalse(task.id)
                }
                val newTasklist = db.todoDao().getAllRows() as ArrayList<Todo>
                todoAdapter.updateTasks(newTasklist)
            }

            override fun deleteClicked(task: Todo, position: Int) {
                db.todoDao().delete(task)
                val newTasklist = db.todoDao().getAllRows() as ArrayList<Todo>
                todoAdapter.updateTasks(newTasklist)
            }

        }
        lvTODO.adapter = todoAdapter


        btnADD.setOnClickListener {
            db.todoDao().insertRow(
                Todo(
                    task = etTask.text.toString(),
                    status = false
                )
            )

            etTask.text.clear()
            list = db.todoDao().getAllRows()
            todoAdapter.updateTasks(list as ArrayList<Todo>)
        }
    }
}
