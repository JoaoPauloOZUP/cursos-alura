package com.example.agenda.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.agenda.R
import com.example.agenda.dao.StudentDAO
import com.example.agenda.model.Student
import com.example.agenda.ui.util.ConstSharedActivities

class FormCreateStudentActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "New student"
        private const val CREATED_STUDENT = "Created student"
    }

    private val studentDao = StudentDAO()
    private lateinit var nameField: EditText
    private lateinit var phoneField: EditText
    private lateinit var emailField: EditText
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
            name = nameField.text.toString(),
            phone = phoneField.text.toString(),
            email = emailField.text.toString()
        )
    }

    private fun saveStudent(student: Student) {
        studentDao.save(student)
        Toast.makeText(this, CREATED_STUDENT, ConstSharedActivities.DURATION_TOAST).show()
        finish()
    }

    private fun initializeAttributesViews() {
        nameField = findViewById(R.id.activity_form_students_name)
        phoneField = findViewById(R.id.activity_form_students_phone)
        emailField = findViewById(R.id.activity_form_students_email)
        btnSaveStudent = findViewById(R.id.activity_form_students_btn_save)
    }

    private fun configureButtonSaveStudent() {
        btnSaveStudent.setOnClickListener {
            val student = createStudent()
            saveStudent(student)
        }
    }
}