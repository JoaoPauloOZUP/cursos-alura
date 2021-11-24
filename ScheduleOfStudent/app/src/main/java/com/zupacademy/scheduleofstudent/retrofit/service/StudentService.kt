package com.zupacademy.scheduleofstudent.retrofit.service

import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentRequest
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentResponse
import retrofit2.Call
import retrofit2.http.*

interface StudentService {

    @GET("/api/student")
    fun getAllStudents(): Call<List<StudentResponse>>

    @POST("/api/student/save")
    fun saveStudent(@Body response: StudentRequest): Call<StudentResponse>

    @POST("/api/student/{id}")
    fun editStudent(@Path("id") id: Long, @Body request: StudentRequest): Call<StudentResponse>

    @DELETE("/api/student/{id}")
    fun deleteStudent(@Path("id") id: Long): Call<Unit>

    @POST("/api/student/indice/{id}/{indice}")
    fun updateIndice(@Path("id") id: Long, @Path("indice") indice: Int): Call<Unit>
}