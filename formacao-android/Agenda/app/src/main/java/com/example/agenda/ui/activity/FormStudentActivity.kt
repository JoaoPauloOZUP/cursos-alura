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
    private lateinit var nameField: EditText
    private lateinit var phoneField: EditText
    private lateinit var emailField: EditText
    private lateinit var btnSaveStudent: Button
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_student)
        title = TITLE_APPBAR
        initializeAttributesViews()
        configureButtonSaveStudent()
        verifyIntent()
    }

    private fun createStudent(): Student {
        return student?.apply {
            name = nameField.text.toString()
            phone = phoneField.text.toString()
            email = emailField.text.toString()
        } ?:
        Student(
            name = nameField.text.toString(),
            phone = phoneField.text.toString(),
            email = emailField.text.toString()
        )
    }

    private fun saveStudent(student: Student) {
        studentDao.save(student)
        Toast.makeText(this, CREATED_STUDENT, DURATION).show()
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

    private fun verifyIntent() {
        if(intentIsNotEmpty()) {
            student = intent.extras!!.getSerializable(Student::javaClass.name) as Student
            configureFormForEdit(student)
        }
    }

    private fun intentIsNotEmpty(): Boolean {
        return intent.hasExtra(Student::javaClass.name)
    }

    private fun configureFormForEdit(student: Student?) {
        student!!.run {
            nameField.setText(name)
            phoneField.setText(phone)
            emailField.setText(email)
        }
    }
}