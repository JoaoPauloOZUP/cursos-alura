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

    private lateinit var listStudent: List<Student>
    private lateinit var listView: ListView
    private lateinit var fabNewStudent: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Students"
        setContentView(R.layout.activity_list_students)
        println("ON_CREATE")

        fabNewStudent = findViewById(R.id.activity_list_students_fab_new_student)
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

        listStudent = StudentDAO().allStudents()
        listView = findViewById(R.id.activity_list_students_listview)
        listView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listStudent
        )
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