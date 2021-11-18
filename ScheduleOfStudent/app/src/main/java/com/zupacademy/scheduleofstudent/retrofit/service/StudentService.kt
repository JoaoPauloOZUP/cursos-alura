package com.zupacademy.scheduleofstudent.retrofit.service

import com.zupacademy.scheduleofstudent.database.entity.StudentTest
import retrofit2.Call
import retrofit2.http.GET

interface StudentService {

    @GET("/api/student")
    fun getAllStudents(): Call<List<Unit>>
}