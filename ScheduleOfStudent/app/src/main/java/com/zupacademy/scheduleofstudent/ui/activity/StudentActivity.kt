package com.zupacademy.scheduleofstudent.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentRequest
import com.zupacademy.scheduleofstudent.ui.fragment.*
import com.zupacademy.scheduleofstudent.ui.fragment.extension.fragmentTransaction
import com.zupacademy.scheduleofstudent.ui.shared.EXTRA_STUDENT

class StudentActivity : AppCompatActivity(), Listener, StudentEditExtra, StudentCreateExtra {

    companion object {
        private const val APPBAR_TITLE = "Student list"
    }

    private lateinit var callbackNewStudentFragment: (student: StudentRequest) -> Unit
    private lateinit var callbackEditStudentFragment: (student: Student) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        title = APPBAR_TITLE
        if(savedInstanceState == null) {
            startStudentListFragment()
        }
    }

    private fun startStudentListFragment() {
        fragmentTransaction {
            replace(R.id.activity_main_container, StudentListFragment())
        }
    }

    private fun startStudentCreateFormFragment() {
        val fragment = StudentFormCreateFragment()

        fragmentTransaction {
            replace(R.id.activity_main_container, fragment)
            addToBackStack(null)
        }
    }

    private fun startStudentEditFormFragment(student: Student) {
        val data = Bundle()
        val fragment = StudentFormEditFragment()

        data.putSerializable(EXTRA_STUDENT, student)
        fragment.arguments = data

        fragmentTransaction {
            replace(R.id.activity_main_container, fragment)
            addToBackStack(null)
        }
    }

    override fun startFormStudentFragment(callbackNewStudentFragment: (student: StudentRequest) -> Unit) {
       startStudentCreateFormFragment()
        this.callbackNewStudentFragment = callbackNewStudentFragment
    }

    override fun startFormStudentFragmentWithExtra(student: Student, callbackEditStudentFragment: (student: Student) -> Unit) {
        startStudentEditFormFragment(student)
        this.callbackEditStudentFragment = callbackEditStudentFragment
    }

    override fun extraValueForEdit(student: Student) {
        callbackEditStudentFragment(student)
        startStudentListFragment()
    }

    override fun extraValueForCreate(studentRequest: StudentRequest) {
        callbackNewStudentFragment(studentRequest)
        startStudentListFragment()
    }
}