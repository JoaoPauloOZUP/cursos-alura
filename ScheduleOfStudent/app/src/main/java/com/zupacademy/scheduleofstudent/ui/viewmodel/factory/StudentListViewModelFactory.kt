package com.zupacademy.scheduleofstudent.ui.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zupacademy.scheduleofstudent.database.repository.StudentRepository
import com.zupacademy.scheduleofstudent.ui.viewmodel.StudentListViewModel

class StudentListViewModelFactory(
    private val repository: StudentRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StudentListViewModel(repository) as T
    }
}