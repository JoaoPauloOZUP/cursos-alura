package com.zupacademy.scheduleofstudent.ui.fragment.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.fragmentTransaction(execute: FragmentTransaction.() -> Unit) {
    val transaction = supportFragmentManager.beginTransaction()
    execute(transaction)
    transaction.commit()
}