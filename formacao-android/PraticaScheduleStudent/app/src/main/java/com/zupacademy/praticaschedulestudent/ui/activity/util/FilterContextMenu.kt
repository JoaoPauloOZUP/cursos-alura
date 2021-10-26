package com.zupacademy.praticaschedulestudent.ui.activity.util

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.zupacademy.praticaschedulestudent.model.Student
import com.zupacademy.praticaschedulestudent.repository.StudentRepository
import com.zupacademy.praticaschedulestudent.ui.activity.adapter.StudentCustomAdapter

enum class FilterContextMenu {
    REMOVE {
        override fun action(context: Context, adapter: StudentCustomAdapter, student: Student) {
            AlertDialog.Builder(context)
                .setTitle("Removing Student")
                .setMessage("Do you want to remove this student")
                .setPositiveButton("Yes") { dialog, which ->
                    val repository = StudentRepository()
                    repository.remove(student)
                    adapter.remove(student)
                }
                .setNegativeButton("No", null)
                .show()
        }
    },
    ;

    abstract fun action(context: Context, adapter: StudentCustomAdapter, student: Student)
}