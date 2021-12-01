package com.zupacademy.scheduleofstudent.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.database.repository.StudentRepository
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentRequest
import com.zupacademy.scheduleofstudent.ui.adapter.StudentRecyclerAdapter
import com.zupacademy.scheduleofstudent.ui.helper.StudentItemTouchCallback
import com.zupacademy.scheduleofstudent.ui.listerner.OnItemClickListener
import com.zupacademy.scheduleofstudent.ui.shared.CREATED_RESULT
import com.zupacademy.scheduleofstudent.ui.shared.EDITED_RESULT
import com.zupacademy.scheduleofstudent.ui.shared.EXTRA_STUDENT
import com.zupacademy.scheduleofstudent.ui.viewmodel.StudentListViewModel
import com.zupacademy.scheduleofstudent.ui.viewmodel.factory.StudentListViewModelFactory
import org.koin.android.ext.android.inject

class StudentList : AppCompatActivity() {

    companion object {
        private const val APPBAR_TITLE = "Student list"
    }

    private lateinit var adapter: StudentRecyclerAdapter
    private lateinit var someActivityResult: ActivityResultLauncher<Intent>
    private val repository: StudentRepository by inject()
    private val factory: StudentListViewModelFactory by inject()
    private val fabNewStudent: FloatingActionButton by lazy {
        findViewById(R.id.student_list_new_student_fab)
    }
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.student_list_recycler_view)
    }
    private val itemTouchHelper: ItemTouchHelper by lazy {
        ItemTouchHelper(StudentItemTouchCallback(adapter))
    }
    private val viewModel by lazy {
        val provider: ViewModelProvider = ViewModelProviders.of(this,  factory)
        provider.get(StudentListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        title = APPBAR_TITLE
        configureAdapter()
        configureFabOnclickListener()
        configureActivityResult()
    }

    private fun configureAdapter() {
        viewModel.findAllStudents(::toast).observe(this, Observer { studentList ->
            adapter = StudentRecyclerAdapter(this, studentList.toMutableList(), repository)
            configureOnItemClickListener()
            recyclerView.adapter = adapter
            configureItemTouchHelper()
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
            override fun onClick(item: Student, position: Int) {
                startFormStudentActivityWithExtra(item)
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
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun activityResult(resultCode: Int, data: Intent?) {
        if (resultCode ==  CREATED_RESULT) {
            val studentRequest = data?.getSerializableExtra(EXTRA_STUDENT) as StudentRequest

            viewModel.saveStudent(studentRequest, ::toast).observe(this, { student ->
                adapter.save(student)
            })
        }

        if (resultCode ==  EDITED_RESULT) {
            val studentEdit = data?.getSerializableExtra(EXTRA_STUDENT) as Student

            viewModel.editStudent(studentEdit, ::toast).observe(this, { student ->
                adapter.edit(student)
            })
        }
    }

    private fun startFormStudentActivityWithExtra(student: Student) {
        someActivityResult.launch(
            Intent(this, FormEditStudent::class.java)
                .putExtra(EXTRA_STUDENT, student)
        )
    }

    private fun toast() {
        Toast.makeText(this, "Error your network!", Toast.LENGTH_SHORT).show()
    }
}