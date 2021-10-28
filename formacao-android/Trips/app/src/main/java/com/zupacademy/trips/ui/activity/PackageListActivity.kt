package com.zupacademy.trips.ui.activity

import TravelPackageDAO
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.zupacademy.trips.R
import com.zupacademy.trips.ui.activity.adapter.PackageListAdapter

class PackageListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_list)
        findViewById<ListView>(R.id.list_travels_package).let { listView ->
            val dao = TravelPackageDAO()
            listView.adapter = PackageListAdapter(this, dao.allTravelPackage().toMutableList())
        }

        startActivity(
            Intent(this, PaymentActivity::class.java)
        )

//        startActivity(
//            Intent(this, TravelPackageSummary::class.java)
//        )
    }
}