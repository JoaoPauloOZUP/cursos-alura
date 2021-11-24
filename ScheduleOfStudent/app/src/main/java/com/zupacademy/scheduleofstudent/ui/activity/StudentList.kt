package com.zupacademy.scheduleofstudent.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.Database
import com.zupacademy.scheduleofstudent.database.dao.StudentDao
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.database.repository.LoadedDataListener
import com.zupacademy.scheduleofstudent.database.repository.StudentRepository
import com.zupacademy.scheduleofstudent.retrofit.StudentRetrofit
import com.zupacademy.scheduleofstudent.retrofit.service.StudentService
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentRequest
import com.zupacademy.scheduleofstudent.ui.adapter.StudentRecyclerAdapter
import com.zupacademy.scheduleofstudent.ui.helper.StudentItemTouchCallback
import com.zupacademy.scheduleofstudent.ui.listerner.OnItemClickListener
import com.zupacademy.scheduleofstudent.ui.shared.CREATED_RESULT
import com.zupacademy.scheduleofstudent.ui.shared.EDITED_RESULT
import com.zupacademy.scheduleofstudent.ui.shared.EXTRA_STUDENT
import org.koin.android.ext.android.inject

class StudentList : AppCompatActivity() {

    companion object {
        private const val APPBAR_TITLE = "Student list"
    }

    private lateinit var fabNewStudent: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentRecyclerAdapter
    private lateinit var someActivityResult: ActivityResultLauncher<Intent>
    private lateinit var itemTouchHelper: ItemTouchHelper
    private val repository: StudentRepository by inject<StudentRepository>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        title = APPBAR_TITLE
        initializeAttributesOnView()
        configureAdapter()
        configureFabOnclickListener()
        configureActivityResult()
    }

    private fun initializeAttributesOnView() {
        fabNewStudent = findViewById(R.id.student_list_new_student_fab)
        recyclerView = findViewById(R.id.student_list_recycler_view)
    }

    private fun initializeDao() {

    }

    private fun configureAdapter() {
        repository.findAllStudents(object: LoadedDataListener() {
            override fun whenLoaded(studentList: List<Student>) {
                adapter = StudentRecyclerAdapter(this@StudentList, studentList.toMutableList(), repository)
                configureOnItemClickListener()
                recyclerView.adapter = adapter
                configureItemTouchHelper()
            }
        })
    }

    private fun configureFabOnclickListener() {
        fabNewStudent.setOnClickListener {
            someActivityResult.launch(
                Intent(this, FormCreateStudent::class.java)
            )
        }
    }

    private fun configureOnItemClickListener() {
        val onItemClickListener = object : OnItemClickListener<Student>(){
            override fun onClick(student: Student, position: Int) {
                startFormStudentActivityWithExtra(student)
            }
        }
        adapter.setOnItemClickListener(onItemClickListener)
    }

    private fun configureActivityResult() {
        someActivityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            activityResult(it.resultCode, it.data)
        }
    }

    private fun configureItemTouchHelper() {
        itemTouchHelper = ItemTouchHelper(StudentItemTouchCallback(adapter))
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun activityResult(resultCode: Int, data: Intent?) {
        if (resultCode ==  CREATED_RESULT) {
            val studentRequest = data?.getSerializableExtra(EXTRA_STUDENT) as StudentRequest

            repository.saveStudent(studentRequest, object: LoadedDataListener() {
                override fun whenLoaded(student: Student) {
                    adapter.save(student)
                }
            })
        }

        if (resultCode ==  EDITED_RESULT) {
            val studentEdit = data?.getSerializableExtra(EXTRA_STUDENT) as Student

            repository.editStudent(studentEdit, object: LoadedDataListener() {
                override fun whenLoaded(student: Student) {
                    adapter.edit(student)
                }
            })
        }
    }

    private fun startFormStudentActivityWithExtra(student: Student) {
        someActivityResult.launch(
            Intent(this, FormEditStudent::class.java)
                .putExtra(EXTRA_STUDENT, student)
        )
    }
}