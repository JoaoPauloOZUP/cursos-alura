package com.zupacademy.trips.ui.activity

import TravelPackageDAO
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.zupacademy.trips.R
import com.zupacademy.trips.ui.activity.adapter.ListPackageAdapter

class PackageListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_list)
        findViewById<ListView>(R.id.list_travels_package).let { listView ->
            val dao = TravelPackageDAO()
            listView.adapter = ListPackageAdapter(this, dao.allTravelPackage().toMutableList())
        }
    }
}