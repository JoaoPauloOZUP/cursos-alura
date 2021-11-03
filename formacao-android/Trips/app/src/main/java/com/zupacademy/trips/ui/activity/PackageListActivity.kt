package com.zupacademy.trips.ui.activity

import TravelPackageDAO
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zupacademy.trips.R
import com.zupacademy.trips.model.TravelPackage
import com.zupacademy.trips.ui.activity.adapter.recycler.OnItemClickListener
import com.zupacademy.trips.ui.activity.adapter.recycler.PackageListAdapterRecycler

class PackageListActivity : AppCompatActivity() {

    private lateinit var recycleview: RecyclerView
    private lateinit var dao: TravelPackageDAO
    private lateinit var adapter: PackageListAdapterRecycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_list)
        initializeAttributes()
        //configureOnItemClickListener()
    }

    override fun onResume() {
        super.onResume()
        configureAdapter()
        configureOnItemClickListener()
    }

    private fun initializeAttributes() {
        recycleview = findViewById(R.id.recyclerlist_travels_package)
        dao = TravelPackageDAO()
    }

    private fun configureAdapter() {
        adapter = PackageListAdapterRecycler(this, dao.allTravelPackage().toMutableList())
        recycleview.adapter = adapter
    }

    private fun configureOnItemClickListener() {
        adapter.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(travelPackage: TravelPackage, position: Int) {
                goToTravelSummary(travelPackage)
            }
        }
    }

    fun goToTravelSummary(extra: TravelPackage) {
        startActivity(
            Intent(this, TravelPackageSummary::class.java)
                .putExtra(TravelPackage::javaClass.name, extra)
        )
    }
}