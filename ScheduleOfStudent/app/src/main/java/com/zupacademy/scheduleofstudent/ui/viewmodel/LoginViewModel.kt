package com.zupacademy.scheduleofstudent.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.zupacademy.scheduleofstudent.database.repository.LoginRepository

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {

    fun login() {
        repository.singIn()
    }

    fun isLogged(): Boolean = repository.isLogged()
}