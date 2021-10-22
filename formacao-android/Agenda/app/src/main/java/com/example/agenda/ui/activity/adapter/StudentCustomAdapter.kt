package com.example.agenda.ui.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.agenda.R
import com.example.agenda.model.Student

class StudentCustomAdapter(
    private val context: Context,
    private var list : MutableList<Student>
) : BaseAdapter() {

    override fun getCount(): Int {
        return list.count()
    }

    override fun getItem(position: Int): Student {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val createdView = createView(parent)
        val student = list[position]
        dataBindOnView(createdView, student)
        return createdView
    }

    fun update(list: List<Student>) {
        clear()
        addAll(list)
        notifyDataSetChanged()
    }

    private fun clear() {
        list.clear()
    }

    private fun addAll(list: List<Student>) {
        this.list.addAll(list)
    }

    fun remove(student: Student) {
        list.remove(student)
        notifyDataSetChanged()
    }

    private fun createView(parent: ViewGroup?): View {
        return LayoutInflater
            .from(context)
            .inflate(R.layout.item_student, parent, false)
    }

    private fun dataBindOnView(createdView: View, student: Student) {
        val nameTextView = createdView.findViewById<TextView>(R.id.item_student_name)
        val phoneTextView = createdView.findViewById<TextView>(R.id.item_student_phone)
        nameTextView.text = student.name
        phoneTextView.text = student.phone
    }
}