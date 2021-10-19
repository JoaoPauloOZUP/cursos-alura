package com.example.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.R
import com.example.agenda.dao.StudentDAO
import com.example.agenda.model.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListStudentsActivity : AppCompatActivity() {
    
    // Pq uma activity pode se perder no estado de onStop?
    // Ate que ponto variaveis de escopo globais sao okay?

    companion object {
        private const val TITLE_APPBAR = "Students"
    }

    private val studentDAO = StudentDAO()
    private lateinit var listStudent: List<Student>
    private lateinit var listView: ListView
    private lateinit var fabNewStudent: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_students)
        title = TITLE_APPBAR

        initializeAttributesViews()
        configureFabNewStudent()

        println("ON_CREATE")
    }

    override fun onStart() {
        super.onStart()
        println("ON_START")
    }

    override fun onResume() {
        super.onResume()

        initializeAttributesViews()
        configureListStudents()

        println("ON_RESUME")
    }

    override fun onPause() {
        super.onPause()
        println("ON_PAUSE")
    }

    override fun onStop() {
        super.onStop()
        println("ON_STOP")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("ON_DESTROY")
    }

    private fun initializeAttributesViews() {
        listView = findViewById(R.id.activity_list_students_listview)
        fabNewStudent = findViewById(R.id.activity_list_students_fab_new_student)
    }

    private fun configureFabNewStudent() {
        fabNewStudent.setOnClickListener {
            startFormStudentActivity()
        }
    }

    private fun startFormStudentActivity() {
        startActivity(Intent(this, FormStudentActivity::class.java))
    }

    private fun initializeListStudents() {
        listStudent = studentDAO.allStudents()
    }

    private fun configureListStudents() {
        initializeListStudents()
        listView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listStudent
        )
    }
}