package com.example.agenda.ui.activity.util


import com.example.agenda.dao.StudentDAO
import com.example.agenda.model.Student
import com.example.agenda.ui.activity.adapter.StudentCustomAdapter

enum class FilterContextMenu {
    REMOVE {
        override fun action(
            student: Student,
            studentDAO: StudentDAO,
            studentCustomAdapter: StudentCustomAdapter
        ) {
            studentDAO.remove(student)
            studentCustomAdapter.remove(student)
        }
    },
    ;

    abstract fun action(
        student: Student,
        studentDAO: StudentDAO,
        studentCustomAdapter: StudentCustomAdapter
    )
}