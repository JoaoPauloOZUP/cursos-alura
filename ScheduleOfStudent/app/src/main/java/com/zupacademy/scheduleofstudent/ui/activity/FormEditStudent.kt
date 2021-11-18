package com.zupacademy.scheduleofstudent.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.ui.shared.EDITED_RESULT
import com.zupacademy.scheduleofstudent.ui.shared.EXTRA_STUDENT
import com.zupacademy.scheduleofstudent.ui.shared.OPTION_MENU

class FormEditStudent : AppCompatActivity() {

    companion object {
        private const val APPBAR_TITLE = "Edit student"
    }

    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_create_student)
        title = APPBAR_TITLE
        initializeAttributesOnView()
        verifyIntent()
    }

    fun initializeAttributesOnView() {
        name = findViewById(R.id.form_students_name)
        phone = findViewById(R.id.form_students_phone)
        email = findViewById(R.id.form_students_email)
    }

    private fun verifyIntent() {
        if(intentIsNotEmpty()) {
            student = intent.extras!!.getSerializable(EXTRA_STUDENT) as Student
            configureFormEdit(student)
        }
    }

    private fun intentIsNotEmpty(): Boolean {
        return intent.hasExtra(EXTRA_STUDENT)
    }

    private fun configureFormEdit(student: Student?) {
        bind(student)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_student_form_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemSelected = item.title as String
        if(itemSelected == OPTION_MENU) {
            editStudent(student!!)
            setResult(
                EDITED_RESULT,
                Intent(this, StudentList::class.java)
                    .putExtra(EXTRA_STUDENT, student)
            )
        }
        finish()
        return super.onOptionsItemSelected(item)
    }

    fun editStudent(student: Student) {
        student.name = name.text.toString()
        student.phone = phone.text.toString()
        student.email = email.text.toString()
    }

    fun bind(student: Student?) {
        student?.let { student ->
            name.setText(student.name)
            phone.setText(student.phone)
            email.setText(student.email)
        }
    }
}