package com.zupacademy.trips.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.zupacademy.trips.R

class PaymentActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "Payment"
    }

    private lateinit var btnPurchaseFinish: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        title = TITLE_APPBAR
        dataBindOnViewPrice()
        initializeAttributes()
        configureBtnPurchaseFinishClickListener()
    }

    private fun dataBindOnViewPrice() {
        findViewById<TextView>(R.id.payment_purchase_price).let { purchasePrice ->
            purchasePrice.text = "243,99"
        }
    }

    private fun initializeAttributes() {
        btnPurchaseFinish = findViewById(R.id.payment_purchase_finish)
    }

    private fun configureBtnPurchaseFinishClickListener() {
        btnPurchaseFinish.setOnClickListener {
            startActivity(
                Intent(this, PurchaseSummaryActivity::class.java)
            )
        }
    }

}