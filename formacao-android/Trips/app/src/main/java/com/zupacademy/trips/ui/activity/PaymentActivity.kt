package com.zupacademy.trips.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zupacademy.trips.R

class PaymentActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "Payment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        title = TITLE_APPBAR
    }
}