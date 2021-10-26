package com.zupacademy.praticaschedulestudent.ui.activity.util

import com.zupacademy.praticaschedulestudent.model.Student
import com.zupacademy.praticaschedulestudent.repository.StudentRepository
import com.zupacademy.praticaschedulestudent.shared.toast
import com.zupacademy.praticaschedulestudent.ui.activity.util.ConstForActivites.*

enum class FilterOptionMenu {
    SAVE {
        override fun action(student: Student) {
            val repository = StudentRepository()
            repository.save(student)
        }
    },
    ;

    abstract fun action(student: Student)
}