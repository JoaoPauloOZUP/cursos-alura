package com.example.agenda.dao

import com.example.agenda.model.Student

class StudentDAO {

    companion object{
        private val listStudent: MutableList<Student> = mutableListOf(
            Student("Joao", "199999999", "joao@mail.com"),
            Student("Giovana", "1999999", "gio@mail.com"),
            Student("Kiara", "1099000", "ki@mail.com")
        )
    }

    fun save(student: Student) {
        listStudent.add(student)
    }

    fun allStudents(): List<Student> {
        return listStudent.toList()
    }
}
