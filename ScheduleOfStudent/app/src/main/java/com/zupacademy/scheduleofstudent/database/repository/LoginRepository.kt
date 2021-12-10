package com.zupacademy.scheduleofstudent.database.repository

import android.content.SharedPreferences
import androidx.core.content.edit

private const val LOGGED_USER = "logged"

class LoginRepository(
    private val preferences: SharedPreferences
) {

    fun singIn() {
        preferences.edit {
            putBoolean(LOGGED_USER, true)
        }
    }

    fun logout() {
        preferences.edit {
            putBoolean(LOGGED_USER, false)
        }
    }

    fun isLogged(): Boolean = preferences.getBoolean(LOGGED_USER, false)
}