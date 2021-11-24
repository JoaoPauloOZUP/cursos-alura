package com.zupacademy.scheduleofstudent.retrofit.service.dto

import com.zupacademy.scheduleofstudent.database.entity.Student

class StudentResponse(
    var id: Long,
    var name: String,
    var phone: String,
    var email: String,
    var indice: Int?
) {

    fun toStudent(): Student = Student(id, name, phone, email).apply { indice = this@StudentResponse.indice  }
}