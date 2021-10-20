package com.example.agenda.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.R
import com.example.agenda.dao.StudentDAO
import com.example.agenda.model.Student
import com.example.agenda.ui.util.ConstSharedActivities

class FormEditStudentActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "Edit student"
        private const val EDITED_STUDENT = "Edited student"
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

    private fun saveStudent(student: Student) {
        studentDao.save(student)
        Toast.makeText(this, EDITED_STUDENT, ConstSharedActivities.DURATION_TOAST).show()
        finish()
    }

    private fun editStudent(): Student {
        return student!!.apply {
            name = nameField.text.toString()
            phone = phoneField.text.toString()
            email = emailField.text.toString()
        }
    }

    private fun configureButtonSaveStudent() {
        btnSaveStudent.setOnClickListener {
            val student = editStudent()
            saveStudent(student)
        }
    }

    private fun initializeAttributesViews() {
        nameField = findViewById(R.id.activity_form_students_name)
        phoneField = findViewById(R.id.activity_form_students_phone)
        emailField = findViewById(R.id.activity_form_students_email)
        btnSaveStudent = findViewById(R.id.activity_form_students_btn_save)
    }

    private fun intentIsNotEmpty(): Boolean {
        return intent.hasExtra(ConstSharedActivities.EXTRA_STUDENT)
    }

    private fun verifyIntent() {
        if(intentIsNotEmpty()) {
            student = intent.extras!!.getSerializable(ConstSharedActivities.EXTRA_STUDENT) as Student
            configureFormForEdit(student)
        }
    }

    private fun configureFormForEdit(student: Student?) {
        student!!.run {
            nameField.setText(name)
            phoneField.setText(phone)
            emailField.setText(email)
        }
    }
}