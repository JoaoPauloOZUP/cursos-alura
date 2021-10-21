package com.example.agenda.ui.activity.util

import android.widget.ArrayAdapter
import com.example.agenda.dao.StudentDAO
import com.example.agenda.model.Student

enum class FilterContextMenu {
    REMOVE {
        override fun action(
            student: Student,
            studentDAO: StudentDAO,
            arrayAdapterStudent: ArrayAdapter<Student>
        ) {
            studentDAO.remove(student)
            arrayAdapterStudent.remove(student)
            arrayAdapterStudent.notifyDataSetChanged()
        }
    },
    ;

    abstract fun action(
        student: Student,
        studentDAO: StudentDAO,
        arrayAdapterStudent: ArrayAdapter<Student>
    )
}