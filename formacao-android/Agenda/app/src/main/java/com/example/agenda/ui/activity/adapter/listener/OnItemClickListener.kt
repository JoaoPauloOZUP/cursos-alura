package com.example.agenda.ui.activity.adapter.listener

import com.example.agenda.model.Student

interface OnItemClickListener {
    fun onItemClick(student: Student, position: Int)
}