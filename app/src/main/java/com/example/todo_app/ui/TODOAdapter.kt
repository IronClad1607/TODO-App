package com.example.todo_app.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.example.todo_app.R
import com.example.todo_app.database.Todo

class TODOAdapter(var tasks: ArrayList<Todo>) : BaseAdapter() {

    var listItemListener : ListItemListener? = null

    fun updateTasks(newTasks: ArrayList<Todo>) {
        tasks.clear()
        tasks.addAll(newTasks)
        notifyDataSetChanged()
    }


    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val li =p2!!.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = p1?:li.inflate(R.layout.list_todo,p2,false)

        view.findViewById<TextView>(R.id.tvTask).text = getItem(position).task


        if(getItem(position).status)
        {
            view.findViewById<CheckBox>(R.id.cbDone).isChecked = true
            view.findViewById<TextView>(R.id.tvTask).setTextColor(Color.BLUE)
        }
        else{
            view.findViewById<CheckBox>(R.id.cbDone).isChecked = false
            view.findViewById<TextView>(R.id.tvTask).setTextColor(Color.BLACK)
        }

        view.findViewById<CheckBox>(R.id.cbDone).setOnClickListener {
            listItemListener?.checkClicked(getItem(position),position)
        }


        view.findViewById<Button>(R.id.btnCross).setOnClickListener {
            listItemListener?.deleteClicked(getItem(position),position)
        }


        return view
    }

    override fun getItem(p0: Int): Todo = tasks[p0]

    override fun getItemId(p0: Int): Long = 0

    override fun getCount() = tasks.size
}