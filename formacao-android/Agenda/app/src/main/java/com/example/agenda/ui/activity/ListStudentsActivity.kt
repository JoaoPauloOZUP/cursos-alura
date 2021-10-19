package com.example.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
        private const val LYFECICLE = "LYFECICLE"
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

        Log.i(LYFECICLE, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i(LYFECICLE, "onStart")
    }

    override fun onResume() {
        super.onResume()

        initializeAttributesViews()
        configureListStudents()

        Log.i(LYFECICLE, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(LYFECICLE, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(LYFECICLE, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(LYFECICLE, "onDestroy")
    }

    private fun initializeAttributesViews() {
        listView = findViewById(R.id.activity_list_students_listview)
        fabNewStudent = findViewById(R.id.activity_list_students_fab_new_student)
    }

    private fun configureFabNewStudent() {
        fabNewStudent.setOnClickListener {
            startFormStudentActivityWithExtra()
        }
    }

    private fun startFormStudentActivityWithExtra() {
        startActivity(Intent(this, FormStudentActivity::class.java))
    }

    private fun startFormStudentActivityWithExtra(extra: Student) {
        startActivity(
            Intent(this, FormStudentActivity::class.java)
                .putExtra(Student::javaClass.name, extra)
        )
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

        listView.setOnItemClickListener { parent, view, position, id ->
            listStudent[position].run {
                startFormStudentActivityWithExtra(this)
            }
        }
    }
}