package com.zupacademy.trips.ui.activity

import TravelPackageDAO
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zupacademy.trips.R
import com.zupacademy.trips.model.TravelPackage
import com.zupacademy.trips.ui.activity.adapter.PackageListAdapter

class PackageListActivity : AppCompatActivity() {

    private lateinit var recycleview: RecyclerView
    private lateinit var dao: TravelPackageDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_list)
        initializeAttributes()
        configureOnItemClickListener()
    }

    override fun onResume() {
        super.onResume()
        configureAdapter()
    }

    private fun initializeAttributes() {
        recycleview = findViewById(R.id.recyclelist_travels_package)
        dao = TravelPackageDAO()
    }

    private fun configureAdapter() {
        recycleview.adapter = PackageListAdapter(this, dao.allTravelPackage().toMutableList())
    }

    private fun configureOnItemClickListener() {
        recycleview.setOnItemClickListener { parent, view, position, id ->
            val travelPackageClicked = dao.getPackage(position)
            startActivity(
                Intent(this, TravelPackageSummary::class.java)
                    .putExtra(TravelPackage::javaClass.name, travelPackageClicked)
            )
        }
    }
}