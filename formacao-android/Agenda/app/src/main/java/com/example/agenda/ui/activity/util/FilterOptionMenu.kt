package com.example.agenda.ui.activity.util

import com.example.agenda.dao.StudentDAO
import com.example.agenda.model.Student

enum class FilterOptionMenu {
    SAVE {
        override fun action(student: Student, studentDAO: StudentDAO) {
            studentDAO.save(student)
        }
    },
    ;

    abstract fun action(student: Student, studentDAO: StudentDAO)
}