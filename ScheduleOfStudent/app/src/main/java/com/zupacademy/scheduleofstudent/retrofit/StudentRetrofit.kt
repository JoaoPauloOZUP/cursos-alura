package com.zupacademy.scheduleofstudent.retrofit

import com.zupacademy.scheduleofstudent.retrofit.service.StudentService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentRetrofit() {

    private val logging = HttpLoggingInterceptor().setLevel(BODY)

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build();


    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.100.84:8080/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val studentService: StudentService = retrofit.create(StudentService::class.java)
}