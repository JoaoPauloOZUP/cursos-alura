package com.example.agenda.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.agenda.R
import com.example.agenda.model.Aluno

class FormStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "New student"
        setContentView(R.layout.activity_form_student)

        val name = findViewById<EditText>(R.id.activity_form_students_name)
        val phone = findViewById<EditText>(R.id.activity_form_students_phone)
        val email = findViewById<EditText>(R.id.activity_form_students_email)
        val btnSaveStudent = findViewById<Button>(R.id.activity_form_students_btn_save)

        btnSaveStudent.setOnClickListener {
                val newStudent = Aluno(
                    name = name.text.toString(),
                    phone = phone.text.toString(),
                    email = email.text.toString()
                )

                println(newStudent)
                Toast.makeText(this, "Aluno novo cadastrado", Toast.LENGTH_SHORT).show()
        }
    }
}