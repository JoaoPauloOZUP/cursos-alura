package com.example.agenda.ui.activity.util


import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.agenda.dao.StudentDAO
import com.example.agenda.model.Student
import com.example.agenda.ui.activity.adapter.StudentCustomAdapter

enum class FilterContextMenu {
    REMOVE {
        override fun action(
            student: Student,
            studentDAO: StudentDAO,
            studentCustomAdapter: StudentCustomAdapter,
            context: Context
        ) {
            AlertDialog.Builder(context)
                .setTitle("REMOVING STUDENT")
                .setMessage("Do you want to remove this student?")
                .setPositiveButton("YES") { dialog, which ->
                    studentDAO.remove(student)
                    studentCustomAdapter.remove(student)
                }
                .setNegativeButton("NO", null)
                .show()
        }
    },
    ;

    abstract fun action(
        student: Student,
        studentDAO: StudentDAO,
        studentCustomAdapter: StudentCustomAdapter,
        context: Context
    )
}