package com.zupacademy.trips.ui.activity.adapter.recycler

import com.zupacademy.trips.model.TravelPackage

interface OnItemClickListener {
    fun onItemClick(travelPackage: TravelPackage)
}