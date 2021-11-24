package com.zupacademy.scheduleofstudent.database.repository

import android.content.Context
import android.widget.Toast
import com.zupacademy.scheduleofstudent.database.dao.StudentDao
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.retrofit.service.StudentService
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentRequest
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.Comparator

class StudentRepository(
    private val context: Context,
    private val dao: StudentDao,
    private val service: StudentService
) {

    fun findAllStudents(callback: LoadedDataListener) {
        val call = service.getAllStudents()
        call.enqueue(object: Callback<List<StudentResponse>>{
            override fun onResponse(
                call: retrofit2.Call<List<StudentResponse>>,
                response: Response<List<StudentResponse>>
            ) {
                if(response.isSuccessful) {
                    val allStudentListResponse = response.body()
                    allStudentListResponse?.let { studentListResponse ->

                        val allStudentList = studentListResponse
                            .map { response -> response.toStudent() }
                            .toList()
                            .sortedBy { student -> student.indice }

                        allStudentList.forEach { println(it) }

                        CoroutineScope(IO).launch {
                            dao.save(allStudentList)
                            withContext(Main) {
                                callback.whenLoaded(allStudentList)
                            }
                        }
                    }
                } else {
                    loadFromDatabase(callback)
                }
            }

            override fun onFailure(call: retrofit2.Call<List<StudentResponse>>, t: Throwable) {
                loadFromDatabase(callback)
            }
        })
    }

    fun saveStudent(studentRequest: StudentRequest, callback: LoadedDataListener) {
        val call = service.saveStudent(studentRequest)
        call.enqueue(object: Callback<StudentResponse>{
            override fun onResponse(
                call: retrofit2.Call<StudentResponse>,
                response: Response<StudentResponse>
            ) {
                val studentResponse = response.body()
                val managedStudent = studentResponse!!.toStudent()

                CoroutineScope(IO).launch {
                    dao.edit(managedStudent)

                    withContext(Main) {
                        callback.whenLoaded(managedStudent)
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<StudentResponse>, t: Throwable) {
                toast()
            }
        })
    }

    fun editStudent(student: Student, callback: LoadedDataListener) {
        val call = service.editStudent(student.id, StudentRequest(student))
        call.enqueue(object: Callback<StudentResponse> {
            override fun onResponse(
                call: Call<StudentResponse>,
                response: Response<StudentResponse>
            ) {
                val studentResponse = response.body()

                CoroutineScope(IO).launch {
                    studentResponse?.let { response ->
                        val student = response.toStudent()
                        dao.edit(student)
                    }

                    withContext(Main) {
                        print(student)
                        callback.whenLoaded(student)
                    }
                }
            }

            override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                toast()
            }
        })
    }

    fun deleteStudent(student: Student, callback: () -> Unit) {
        CoroutineScope(IO).launch {
            val call = service.deleteStudent(student.id).execute()
            if(call.isSuccessful) {
                dao.remove(student)

                withContext(Main) {
                    callback()
                }
            } else {
                toast()
            }
        }
    }

    fun updateIndice(id: Long, adapterPosition: Int) {
        println(id)
        CoroutineScope(IO).launch {
            val call = service.updateIndice(id, adapterPosition).execute()
            if(call.isSuccessful) {
                dao.updateIndice(id, adapterPosition)
            } else {
                toast()
            }
        }
    }

    fun loadFromDatabase(callback: LoadedDataListener) {
        CoroutineScope(IO).launch {
            val allStudentList = dao.allStudents()
            print(allStudentList)
            withContext(Main) {
                callback.whenLoaded(allStudentList)
            }
        }
    }

    private fun toast() {
        Toast.makeText(context, "Connection error. Check your internet", Toast.LENGTH_SHORT)
    }
}

abstract class LoadedDataListener {
    open fun whenLoaded(student: Student) {}
    open fun whenLoaded(studentList: List<Student>) {}
}