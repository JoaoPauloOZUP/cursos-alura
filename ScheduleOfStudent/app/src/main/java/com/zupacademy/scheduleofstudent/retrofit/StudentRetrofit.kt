package com.zupacademy.scheduleofstudent.retrofit

import com.zupacademy.scheduleofstudent.retrofit.service.StudentService
import retrofit2.Retrofit

class StudentRetrofit() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.100.84:8080/")
        .build()

    val studentService: StudentService = retrofit.create(StudentService::class.java)
}