package com.example.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.R
import com.example.agenda.dao.StudentDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListStudentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Students"
        setContentView(R.layout.activity_list_students)
        println("ON_CREATE")

        val listStudents = StudentDAO().allStudents()
        val listView = findViewById<ListView>(R.id.activity_list_students_listview)
        val fabNewStudent = findViewById<FloatingActionButton>(R.id.activity_list_students_fab_new_student)

        listView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listStudents
        )

        fabNewStudent.setOnClickListener {
            startActivity(Intent(this, FormStudentActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        println("ON_START")
    }

    override fun onResume() {
        super.onResume()
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
}