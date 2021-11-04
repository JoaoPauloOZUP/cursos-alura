package com.example.agenda.ui.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.*
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.R
import com.example.agenda.model.Student
import com.example.agenda.ui.activity.adapter.listener.OnItemClickListener

class StudentRecyclerAdapter(
    private val context: Context,
    private val listStudent: MutableList<Student>,
    private val lifecycle: Lifecycle
) : RecyclerView.Adapter<StudentRecyclerAdapter.ViewHolder>(), LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    private lateinit var onItemClickListener: OnItemClickListener

    inner class ViewHolder(
        view: View,
        onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(view) {

        init {
            view.setOnClickListener {
                val student = listStudent[adapterPosition]
                val position = adapterPosition
                onItemClickListener.onItemClick(student, position)
            }

            view.setOnLongClickListener {
                return@setOnLongClickListener false
            }
        }

        val name = view.findViewById<TextView>(R.id.item_student_name)
        val phone = view.findViewById<TextView>(R.id.item_student_phone)

        fun bind(student: Student) {
            name.text = student.name
            phone.text = student.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.item_student, parent, false)

        return ViewHolder(createdView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listStudent[position].let { student ->
            holder.bind(student)
        }
    }

    override fun getItemCount(): Int {
        return listStudent.count()
    }

    fun save(student: Student) {
        listStudent.add(student)
    }

    fun edit(student: Student, position: Int) {
        listStudent[position] = student
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun remove(position: Int) {
        listStudent.removeAt(position)
    }

    @OnLifecycleEvent(ON_RESUME)
    fun updateDataSet() {
        notifyDataSetChanged()
    }
}