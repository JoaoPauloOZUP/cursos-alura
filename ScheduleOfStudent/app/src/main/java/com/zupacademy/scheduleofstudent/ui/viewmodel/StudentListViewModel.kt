package com.zupacademy.scheduleofstudent.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.database.repository.StudentRepository
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentRequest
import javax.security.auth.callback.Callback

class StudentListViewModel(
    private val repository: StudentRepository
) : ViewModel() {

    fun findAllStudents(error: () -> Unit): LiveData<List<Student>> {
        return repository.findAllStudents(error)
    }

    fun saveStudent(studentRequest: StudentRequest, error: () -> Unit): LiveData<Student> {
        return repository.saveStudent(studentRequest,  error)
    }

    fun editStudent(studentEdit: Student, error: () -> Unit): LiveData<Student> {
        return repository.editStudent(studentEdit, error)
    }
}