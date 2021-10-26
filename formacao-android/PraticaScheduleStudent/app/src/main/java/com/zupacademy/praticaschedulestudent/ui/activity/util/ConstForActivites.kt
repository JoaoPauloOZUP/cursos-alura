package com.zupacademy.praticaschedulestudent.ui.activity.util

import com.zupacademy.praticaschedulestudent.model.Student

enum class ConstForActivites {
    TITLE_APPBAR_LIST_STUDENT {
        override fun value(): String {
            return "Schedule of Students"
        }
    },
    TITLE_APPBAR_CREATE_FORM {
        override fun value(): String {
            return "New Student"
        }
    },
    TITLE_APPBAR_EDIT_FORM {
        override fun value(): String {
            return "Edit Student"
        }
    },
    CREATED_STUDENT {
        override fun value(): String {
            return "Created Student"
        }
    },
    EDITED_STUDENT {
        override fun value(): String {
            return "Edited Student"
        }
    },
    EXTRA_INTENT_EDIT_STUDENT {
        override fun value(): String {
            return Student::javaClass.name
        }
    }
    ;
    abstract fun value(): String
}