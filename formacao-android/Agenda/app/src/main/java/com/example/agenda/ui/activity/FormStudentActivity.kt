package com.example.agenda.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.agenda.R
import com.example.agenda.dao.StudentDAO
import com.example.agenda.model.Student

class FormStudentActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "New student"
        private const val CREATED_STUDENT = "Created student"
        private const val DURATION = Toast.LENGTH_SHORT
    }

    private val studentDao = StudentDAO()
    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private lateinit var btnSaveStudent: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_student)
        title = TITLE_APPBAR
        initializeAttributesViews()
        configureButtonSaveStudent()
    }

    private fun createStudent(): Student {
        return Student(
            name = name.text.toString(),
            phone = phone.text.toString(),
            email = email.text.toString()
        )
    }

    private fun saveStudent(student: Student) {
        studentDao.save(student)
        Toast.makeText(this, CREATED_STUDENT, DURATION).show()
        finish()
    }

    private fun initializeAttributesViews() {
        name = findViewById(R.id.activity_form_students_name)
        phone = findViewById(R.id.activity_form_students_phone)
        email = findViewById(R.id.activity_form_students_email)
        btnSaveStudent = findViewById(R.id.activity_form_students_btn_save)
    }

    private fun configureButtonSaveStudent() {
        btnSaveStudent.setOnClickListener {
            val student = createStudent()
            saveStudent(student)
        }
    }
}