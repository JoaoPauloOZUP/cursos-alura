package com.zupacademy.scheduleofstudent.retrofit.service.dto

import com.zupacademy.scheduleofstudent.database.entity.Student
import java.io.Serializable

class StudentRequest(
    var name: String,
    var phone: String,
    var email: String
) : Serializable {

    var indice: Int? = null

    constructor(student: Student) : this(student.name!!, student.phone!!, student.email!!)
}