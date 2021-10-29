package com.zupacademy.trips.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zupacademy.trips.R

class PurchaseSummaryActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "Purchase summary"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase_summary)
    }
}