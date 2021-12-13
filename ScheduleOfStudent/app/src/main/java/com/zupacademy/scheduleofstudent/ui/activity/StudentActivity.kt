package com.zupacademy.scheduleofstudent.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.zupacademy.scheduleofstudent.R

class StudentActivity : AppCompatActivity() {

    private val navigationController: NavController by lazy {
        findNavController(R.id.nav_host_student)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        navigationController.addOnDestinationChangedListener { controller, destination, arguments ->
            title = destination.label
            supportActionBar?.show()
            if(destination.id == R.id.StudentLogin) {
                supportActionBar?.hide()
            }
        }
    }
}