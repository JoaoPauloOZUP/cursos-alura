package com.zupacademy.trips.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.zupacademy.trips.R
import com.zupacademy.trips.model.TravelPackage
import com.zupacademy.trips.util.formatPriceOnView

class PaymentActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "Payment"
    }

    private lateinit var priceView: TextView
    private lateinit var btnPurchaseFinish: Button
    lateinit var travelPackage: TravelPackage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        title = TITLE_APPBAR
        initializeAttributes()
        configureBtnPurchaseFinishClickListener()
    }

    override fun onResume() {
        super.onResume()
        verifyIntent()
    }

    private fun dataBindOnView() {
       priceView.text = formatPriceOnView(travelPackage.price)
    }

    private fun initializeAttributes() {
        priceView = findViewById(R.id.payment_purchase_price)
        btnPurchaseFinish = findViewById(R.id.payment_purchase_finish)
    }

    private fun configureBtnPurchaseFinishClickListener() {
        btnPurchaseFinish.setOnClickListener {
            startActivity(
                Intent(this, PurchaseSummaryActivity::class.java)
                    .putExtra(TravelPackage::javaClass.name, travelPackage)
            )
        }
    }

    private fun verifyIntent() {
        if(intent.hasExtra(TravelPackage::javaClass.name)) {
            travelPackage = intent.extras!!.getSerializable(TravelPackage::javaClass.name) as TravelPackage
            dataBindOnView()
        }
    }
}