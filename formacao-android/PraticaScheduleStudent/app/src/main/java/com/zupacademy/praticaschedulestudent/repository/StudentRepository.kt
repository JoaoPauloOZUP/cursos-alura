package com.zupacademy.praticaschedulestudent.repository

import com.zupacademy.praticaschedulestudent.model.Student
import com.zupacademy.praticaschedulestudent.shared.generateStudentId
import com.zupacademy.praticaschedulestudent.shared.toast
import com.zupacademy.praticaschedulestudent.ui.activity.ListStudentsActivity

class StudentRepository {
    companion object {
        private const val STUDENT_NOTFOUND = "Student Not Found"
        private val listStudent: MutableList<Student> = mutableListOf()
    }

    fun save(student: Student) {
        student.takeIf { it.isEntity() }
            ?.let {
                edit(it)
            }
            ?: student.run {
                id = generateStudentId()
                listStudent.add(student)
            }
    }
    fun allStudents(): List<Student> {
        return listStudent.toList()
    }

    private fun edit(student: Student) {
        val found = findStudent(student)
        found?.let { foundStudent ->
            listStudent.indexOf(foundStudent).let { indice ->
                listStudent[indice] = student
            }
        } ?: toast(ListStudentsActivity(), STUDENT_NOTFOUND)
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
        } ?: toast(ListStudentsActivity(), STUDENT_NOTFOUND)
    }
}