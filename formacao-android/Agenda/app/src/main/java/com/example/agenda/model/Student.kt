package com.example.agenda.model

import java.io.Serializable

data class Student(
    val name: String,
    val phone: String,
    val email: String
    ) : Serializable {

    override fun toString(): String {
        return name
    }
}
