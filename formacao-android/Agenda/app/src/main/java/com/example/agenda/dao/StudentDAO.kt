package com.example.agenda.dao

import com.example.agenda.model.Student

class StudentDAO {

    companion object{
        private val listStudent: MutableList<Student> = mutableListOf()
    }

    fun save(student: Student) {
        listStudent.add(student)
    }

    fun allStudents(): List<Student> {
        return listStudent.toList()
    }
}
