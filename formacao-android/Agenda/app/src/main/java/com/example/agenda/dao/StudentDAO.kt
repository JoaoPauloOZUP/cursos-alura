package com.example.agenda.dao

import android.widget.Toast
import com.example.agenda.model.Student
import com.example.agenda.ui.activity.ListStudentsActivity
import com.example.agenda.ui.activity.util.ConstSharedActivities

class StudentDAO {

    companion object {
        private const val STUDENT_NOTFOUND = "Student Not Found"
        private var GENERATED_ID = 0L
        private val listStudent: MutableList<Student> = mutableListOf(
            Student("Joao", "199999999", "joao@mail.com").apply {
                id = ++GENERATED_ID
            },
            Student("Giovana", "1999999", "gio@mail.com").apply {
                id = ++GENERATED_ID
            },
            Student("Kiara", "1099000", "ki@mail.com").apply {
                id = ++GENERATED_ID
            }
        )
    }

    fun save(student: Student) {
        if (student.isEntity()) {
            edit(student)
        } else {
            student.apply {
                id = ++GENERATED_ID
            }
            listStudent.add(student)
        }
    }

    fun allStudents(): List<Student> {
        return listStudent.toList()
    }

    private fun edit(student: Student) {
        val found = findStudent(student)
        found?.let { foundStudent ->
            val indice = listStudent.indexOf(foundStudent)
            listStudent[indice] = student
        } ?: toast(STUDENT_NOTFOUND)
    }

    private fun findStudent(student: Student): Student? {
        var foundStudent: Student? = null
        listStudent.forEach {
            if (it.id == student.id) {
                foundStudent = it
            }
        }
        return foundStudent
    }

    fun remove(student: Student) {
        val found = findStudent(student)
        found?.let { foundStudent ->
            listStudent.remove(foundStudent)
        } ?: toast(STUDENT_NOTFOUND)
    }

    private fun toast(text: String) {
        Toast.makeText(ListStudentsActivity(), text, ConstSharedActivities.DURATION_TOAST).show()
    }
}