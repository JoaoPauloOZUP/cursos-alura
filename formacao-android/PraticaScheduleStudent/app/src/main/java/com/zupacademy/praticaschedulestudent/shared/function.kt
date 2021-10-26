package com.zupacademy.praticaschedulestudent.shared

import android.content.Context
import android.widget.Toast
import com.zupacademy.praticaschedulestudent.ui.activity.ListStudentsActivity

fun toast(cotext: Context, text: String) {
    Toast.makeText(cotext, text, UtilFunction.DURATION_TOAST).show()
}

fun generateStudentId(): Long {
    return ++UtilFunction.STUDENT_ID
}

private interface UtilFunction {
    companion object {
        const val DURATION_TOAST = Toast.LENGTH_SHORT
        var STUDENT_ID = 0L
    }
}