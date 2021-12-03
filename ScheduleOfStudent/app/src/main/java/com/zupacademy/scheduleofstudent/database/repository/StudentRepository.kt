package com.zupacademy.scheduleofstudent.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import java.net.ConnectException

class StudentRepository(
    private val dao: StudentDao,
    private val service: StudentService
) {

    private val liveDataOfStudentList = MutableLiveData<List<Student>>()
    private val liveDataOfStudent = MutableLiveData<Student>()

    fun findAllStudents(error: () -> Unit): LiveData<List<Student>> {
        val call = service.getAllStudents()
        call.enqueue(object: Callback<List<StudentResponse>> {
            override fun onResponse(
                call: Call<List<StudentResponse>>,
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
                                liveDataOfStudentList.value = allStudentList
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<StudentResponse>>, t: Throwable) {
                loadFromDatabase(error)
            }
        })

        return liveDataOfStudentList
    }

    fun saveStudent(studentRequest: StudentRequest, error: () -> Unit): LiveData<Student> {
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
                }

                liveDataOfStudent.value = managedStudent
            }

            override fun onFailure(call: retrofit2.Call<StudentResponse>, t: Throwable) {
                CoroutineScope(Main).launch {
                    error()
                }
            }
        })
        return liveDataOfStudent
    }

    fun editStudent(student: Student, error: () -> Unit): LiveData<Student> {
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
                        liveDataOfStudent.value = student
                    }
                }
            }

            override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                CoroutineScope(Main).launch {
                    error()
                }
            }
        })
        return liveDataOfStudent
    }

    fun deleteStudent(student: Student, callback: (success: Boolean) -> Unit) {
        CoroutineScope(IO).launch {
            val call = service.deleteStudent(student.id).execute()
            if(call.isSuccessful) {
                dao.remove(student)

                withContext(Main) {
                    callback(true)
                }
            }
        }
    }

    fun updateIndex(id: Long, adapterPosition: Int, error: () -> Unit): LiveData<Unit> {
        CoroutineScope(IO).launch {
            try {
                val call = service.updateIndice(id, adapterPosition).execute()
                if(call.isSuccessful) {
                    dao.updateIndice(id, adapterPosition)
                }
            } catch (e: ConnectException) {
                CoroutineScope(Main).launch {
                    error()
                }
            }
        }

        return MutableLiveData()
    }

    fun loadFromDatabase(error: () -> Unit) {
        CoroutineScope(IO).launch {
            val allStudentList = dao.allStudents()
            withContext(Main) {
                liveDataOfStudentList.value = allStudentList
                error()
            }
        }
    }
}