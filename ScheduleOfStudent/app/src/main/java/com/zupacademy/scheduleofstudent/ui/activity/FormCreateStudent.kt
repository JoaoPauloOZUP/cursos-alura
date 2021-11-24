package com.zupacademy.scheduleofstudent.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentRequest
import com.zupacademy.scheduleofstudent.ui.shared.CREATED_RESULT
import com.zupacademy.scheduleofstudent.ui.shared.EXTRA_STUDENT
import com.zupacademy.scheduleofstudent.ui.shared.OPTION_MENU

class FormCreateStudent : AppCompatActivity() {

    companion object {
        private const val APPBAR_TITLE = "New student"
    }

    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_create_student)
        title = APPBAR_TITLE
        initializeAttributesOnView()
    }

    fun initializeAttributesOnView() {
        name = findViewById(R.id.form_students_name)
        phone = findViewById(R.id.form_students_phone)
        email = findViewById(R.id.form_students_email)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_student_form_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemSelected = item.title as String
        if(itemSelected == OPTION_MENU) {
            val student = createdStudent()
            setResult(
                CREATED_RESULT,
                Intent(this, StudentList::class.java)
                    .putExtra(EXTRA_STUDENT, student)
            )
        }
        finish()
        return super.onOptionsItemSelected(item)
    }

    fun createdStudent(): StudentRequest {
        return StudentRequest(
            name = name.text.toString(),
            phone = phone.text.toString(),
            email = email.text.toString()
        )
    }
}