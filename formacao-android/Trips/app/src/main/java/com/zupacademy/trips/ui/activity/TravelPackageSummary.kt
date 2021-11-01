package com.zupacademy.trips.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.zupacademy.trips.R
import com.zupacademy.trips.model.TravelPackage
import com.zupacademy.trips.util.formatDayOnView
import com.zupacademy.trips.util.formatPriceOnView
import com.zupacademy.trips.util.getDrawableImage
import java.math.BigDecimal

class TravelPackageSummary : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "Summary package"
    }

    lateinit var localNameView: TextView
    lateinit var localImageView: ImageView
    lateinit var daysView: TextView
    lateinit var priceView: TextView
    lateinit var btnMakePayment: Button
    lateinit var travelPackage: TravelPackage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_package_summary)
        title = TITLE_APPBAR
        initializeAttributes()
        configureBtnMakePayment()
    }

    override fun onResume() {
        super.onResume()
        verifyIntent()
    }

    private fun dataBindOnView() {
        localNameView.text = travelPackage.local
        localImageView.setImageDrawable(getDrawableImage(this, travelPackage))
        daysView.text = formatDayOnView(travelPackage.days)
        priceView.text = formatPriceOnView(travelPackage.price)
    }

    private fun initializeAttributes() {
        localNameView = findViewById(R.id.summary_localName)
        localImageView = findViewById(R.id.summary_localImage)
        daysView = findViewById(R.id.summary_days)
        priceView = findViewById(R.id.summary_price)
        btnMakePayment = findViewById(R.id.summary_make_payment)
    }

    private fun configureBtnMakePayment() {
        btnMakePayment.setOnClickListener {
            startActivity(
                Intent(this, PaymentActivity::class.java)
                    .putExtra(TravelPackage::javaClass.name, travelPackage)
            )
            finish()
        }
    }

    private fun verifyIntent() {
        if(intent.hasExtra(TravelPackage::javaClass.name)) {
            travelPackage = intent.extras!!.getSerializable(TravelPackage::javaClass.name) as TravelPackage
            dataBindOnView()
        }
    }
}