package com.zupacademy.scheduleofstudent.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zupacademy.scheduleofstudent.R

class StudentActivity : AppCompatActivity() {

    companion object {
        private const val APPBAR_TITLE = "Student list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        title = APPBAR_TITLE
    }
}