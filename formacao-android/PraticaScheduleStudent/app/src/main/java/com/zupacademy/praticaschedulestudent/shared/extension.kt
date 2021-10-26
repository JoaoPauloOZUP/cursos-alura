package com.zupacademy.praticaschedulestudent.shared

import android.view.MenuItem
import android.widget.EditText

fun EditText.value(): String {
    return this.text.toString()
}

fun MenuItem.titleUppercase(): String {
    return this.title.toString().uppercase()
}