package com.example.agenda.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.agenda.R
import com.example.agenda.dao.StudentDAO
import com.example.agenda.model.Student

class FormStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_student)
        title = "New student"

        val studentDao = StudentDAO()
        val name = findViewById<EditText>(R.id.activity_form_students_name)
        val phone = findViewById<EditText>(R.id.activity_form_students_phone)
        val email = findViewById<EditText>(R.id.activity_form_students_email)
        val btnSaveStudent = findViewById<Button>(R.id.activity_form_students_btn_save)

        btnSaveStudent.setOnClickListener {
            val student = Student(
                name = name.text.toString(),
                phone = phone.text.toString(),
                email = email.text.toString()
            )

            studentDao.save(student)
            Toast.makeText(this, "Novo aluno cadastrado", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, ListStudentsActivity::class.java))
        }
    }
}