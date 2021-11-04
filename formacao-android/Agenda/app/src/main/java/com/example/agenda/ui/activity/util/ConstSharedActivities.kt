package com.example.agenda.ui.activity.util

import android.widget.Toast

interface ConstSharedActivities {
    companion object {
        const val EXTRA_STUDENT = "STUDENTSERIALAZABLE"
        const val DURATION_TOAST = Toast.LENGTH_SHORT
        const val CREATED_STUDENT_REQUEST = 1
        const val EDITED_STUDENT_REQUEST = 2
    }
}