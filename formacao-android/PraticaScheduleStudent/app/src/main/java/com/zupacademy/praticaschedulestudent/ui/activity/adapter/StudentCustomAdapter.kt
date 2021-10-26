package com.zupacademy.praticaschedulestudent.ui.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.Event.*
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.zupacademy.praticaschedulestudent.R
import com.zupacademy.praticaschedulestudent.model.Student

class StudentCustomAdapter(
    lifecycle: Lifecycle,
    private val listStudent: MutableList<Student>,
    private val context: Context
) : BaseAdapter(), LifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(ON_RESUME)
    fun update() {
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return listStudent.count()
    }

    override fun getItem(position: Int): Any {
        return listStudent[position]
    }

    override fun getItemId(position: Int): Long {
        return 0L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val createdView = createView(parent)
        val student = listStudent[position]
        dataBindOnView(createdView, student)
        return createdView
    }

    private fun createView(parent: ViewGroup?): View {
        return LayoutInflater
        .from(context)
        .inflate(R.layout.item_student, parent, false)
    }

    private fun dataBindOnView(view: View, student: Student) {
        val nameTextView = view.findViewById<TextView>(R.id.item_student_name)
        val phoneTextView = view.findViewById<TextView>(R.id.item_student_phone)
        nameTextView.text = student.name
        phoneTextView.text = student.phone
    }

    fun addAll(listStudent: List<Student>) {
        this.listStudent.clear()
        this.listStudent.addAll(listStudent)
    }

    fun remove(student: Student) {
        listStudent.remove(student)
        notifyDataSetChanged()
    }
}