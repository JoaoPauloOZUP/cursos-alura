package com.example.agenda.dao

import com.example.agenda.model.Student

class StudentDAO {

    companion object {
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
        if(student.id != null) {
            edit(student)
        } else {
            student.id = ++GENERATED_ID
            listStudent.add(student)
        }
    }

    fun allStudents(): List<Student> {
        return listStudent.toList()
    }

    private fun edit(student: Student) {
        var foundStudent: Student? = null
        listStudent.forEach {
            if(it.id == student.id) {
                foundStudent = it
            }
        }
        val indice = listStudent.indexOf(foundStudent)
        listStudent[indice] = student
    }
}
