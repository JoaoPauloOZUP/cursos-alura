package com.example.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.R
import com.example.agenda.dao.StudentDAO
import com.example.agenda.model.Student
import com.example.agenda.ui.activity.adapter.StudentRecyclerAdapter
import com.example.agenda.ui.activity.adapter.listener.OnItemClickListener
import com.example.agenda.ui.activity.util.ConstSharedActivities.Companion.CREATED_STUDENT_REQUEST
import com.example.agenda.ui.activity.util.ConstSharedActivities.Companion.EDITED_STUDENT_REQUEST
import com.example.agenda.ui.activity.util.ConstSharedActivities.Companion.EXTRA_STUDENT
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListStudentsActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "Students"
    }

    private val studentDAO = StudentDAO()
    private lateinit var listStudent: List<Student>
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabNewStudent: FloatingActionButton
    private lateinit var adapter: StudentRecyclerAdapter
    private lateinit var someActivityResult: ActivityResultLauncher<Intent>
    private var positionStudentInAdapter: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_students)
        title = TITLE_APPBAR
        configureActivityResult()
        initializeAttributesViews()
        configureFabNewStudent()
        configureListStudents()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun configureOnItemClickListener() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(student: Student, position: Int) {
                positionStudentInAdapter = position
                startFormStudentActivityWithExtra(student)
            }
        })
    }

    private fun initializeAttributesViews() {
        recyclerView = findViewById(R.id.activity_list_students_listview)
        fabNewStudent = findViewById(R.id.activity_list_students_fab_new_student)
    }

    private fun configureActivityResult() {
        someActivityResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            activityResult(it.resultCode, it.data)
        }
    }

    private fun activityResult(resultCode: Int, data: Intent?) {
        if (resultCode == CREATED_STUDENT_REQUEST) {
            val extra = data?.getSerializableExtra(EXTRA_STUDENT) as Student
            saveStudent(extra)
        }

        if (resultCode == EDITED_STUDENT_REQUEST) {
            val extra = data?.getSerializableExtra(EXTRA_STUDENT) as Student
            editStudent(extra)
        }
    }

    private fun saveStudent(student: Student) {
        studentDAO.save(student)
        adapter.save(student)
    }

    private fun editStudent(student: Student) {
        studentDAO.save(student)
        positionStudentInAdapter?.let { positionStudentInAdapter ->
            adapter.edit(student, positionStudentInAdapter)
        }
        positionStudentInAdapter = null
    }

    private fun configureFabNewStudent() {
        fabNewStudent.setOnClickListener {
            startFormStudentActivity()
        }
    }

    private fun startFormStudentActivity() {
        someActivityResult.launch(
            Intent(this, FormCreateStudentActivity::class.java)
        )
    }

    private fun startFormStudentActivityWithExtra(extra: Student) {
        someActivityResult.launch(
            Intent(this, FormEditStudentActivity::class.java)
                .putExtra(EXTRA_STUDENT, extra)
        )
    }

    private fun initializeListStudents() {
        listStudent = studentDAO.allStudents()
    }

    private fun configureListStudents() {
        initializeListStudents()
        adapter = StudentRecyclerAdapter(this, listStudent.toMutableList(), lifecycle)
        recyclerView.adapter = adapter
        configureOnItemClickListener()
        registerForContextMenu(recyclerView)
    }
}