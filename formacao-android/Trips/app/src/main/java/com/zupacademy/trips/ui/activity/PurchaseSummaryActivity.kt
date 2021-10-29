package com.zupacademy.trips.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.zupacademy.trips.R
import com.zupacademy.trips.model.TravelPackage
import com.zupacademy.trips.util.formatPriceOnView
import com.zupacademy.trips.util.getDrawableImage
import java.math.BigDecimal

class PurchaseSummaryActivity : AppCompatActivity() {

    companion object {
        private const val TITLE_APPBAR = "Purchase summary"
    }

    private lateinit var imageLocalView: ImageView
    private lateinit var localNameView: TextView
    private lateinit var stayView: TextView
    private lateinit var currentPriceView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase_summary)
        initializeAttributes()
        dataBindOnView()
    }

    private fun dataBindOnView() {
        val travelPackage = TravelPackage(
            local = "Sao Paulo",
            image = "sao_paulo_sp",
            2,
            BigDecimal(243.99)
        )

        imageLocalView.setImageDrawable(getDrawableImage(this, travelPackage))
        localNameView.text = travelPackage.local
        currentPriceView.text = formatPriceOnView(travelPackage.price)
    }

    private fun initializeAttributes() {
        imageLocalView = findViewById(R.id.purchase_localimage)
        localNameView = findViewById(R.id.purchase_localname)
        stayView = findViewById(R.id.purchase_stay)
        currentPriceView = findViewById(R.id.purchase_current_price)
    }

}