package com.example.agenda.model

data class Student(
    val name: String,
    val phone: String,
    val email: String
    ) {

    override fun toString(): String {
        return name
    }
}
