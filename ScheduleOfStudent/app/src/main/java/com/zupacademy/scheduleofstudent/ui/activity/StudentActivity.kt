package com.zupacademy.scheduleofstudent.ui.activity

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.zupacademy.scheduleofstudent.R
import com.zupacademy.scheduleofstudent.database.entity.Student
import com.zupacademy.scheduleofstudent.retrofit.service.dto.StudentRequest
import com.zupacademy.scheduleofstudent.ui.fragment.*
import com.zupacademy.scheduleofstudent.ui.fragment.extension.fragmentTransaction
import com.zupacademy.scheduleofstudent.ui.shared.EXTRA_STUDENT

class StudentActivity : AppCompatActivity(), Listener, StudentEditExtra, StudentCreateExtra {

    companion object {
        private const val APPBAR_TITLE = "Student list"
        private const val VIEW_STUDENT_LIST = "student_list"
        private const val VIEW_STUDENT_CREATE = "student_create"
        private const val VIEW_STUDENT_EDIT = "student_edit"
        private const val ROOT_WIDTH_PIXELS_IN_PORTRAIT = 2148
    }

    private val displayMetrics: DisplayMetrics by lazy {
        resources.displayMetrics
    }
    private lateinit var callbackNewStudentFragment: (student: StudentRequest) -> Unit
    private lateinit var callbackEditStudentFragment: (student: Student) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        title = APPBAR_TITLE
//        if(savedInstanceState == null) {
//            startStudentListFragment()
//        } else {
//            supportFragmentManager.findFragmentByTag(VIEW_STUDENT_EDIT)?.let { fragment ->
//                val arguments = fragment.arguments
//                val newFragment = StudentFormEditFragment()
//                newFragment.arguments = arguments
//
//                fragmentTransaction {
//                    remove(fragment)
//                }
//                supportFragmentManager.popBackStack()
//
//                fragmentTransaction {
//                    val container = verifyContainer()
//                    replace(container, newFragment, VIEW_STUDENT_EDIT)
//                }
//            }
//        }
    }

    private fun startStudentListFragment() {
        fragmentTransaction {
            val container = verifyContainer()
            replace(container, StudentListFragment(), VIEW_STUDENT_LIST)
        }
    }

    private fun startStudentCreateFormFragment() {
        val fragment = StudentFormCreateFragment()

        fragmentTransaction {
            val container = verifyContainer()
            replace(container, fragment, VIEW_STUDENT_CREATE)
        }
    }

    private fun startStudentEditFormFragment(student: Student) {
        val data = Bundle()
        val fragment = StudentFormEditFragment()

        data.putSerializable(EXTRA_STUDENT, student)
        fragment.arguments = data

        fragmentTransaction {
            val container = verifyContainer()
            replace(container, fragment, VIEW_STUDENT_EDIT)
        }
    }

    private fun FragmentTransaction.verifyContainer(): Int {
        if(displayMetrics.widthPixels > ROOT_WIDTH_PIXELS_IN_PORTRAIT) {
            return R.id.activity_second_container
        }
        addToBackStack(null)
        return R.id.activity_main_container
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