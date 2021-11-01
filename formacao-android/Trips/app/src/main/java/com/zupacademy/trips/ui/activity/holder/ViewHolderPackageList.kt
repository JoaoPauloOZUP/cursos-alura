package com.zupacademy.trips.ui.activity.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zupacademy.trips.R

// Por que utilizar ViewHolder?
class ViewHolderPackageList(
    view: View
) : RecyclerView.ViewHolder(view) {
    val localNameView: TextView = view.findViewById(R.id.list_package_special)
    val localImageView: ImageView = view.findViewById(R.id.item_travel_package_localimage)
    val daysView: TextView = view.findViewById(R.id.item_travel_package_days)
    val priceView: TextView = view.findViewById(R.id.item_travel_package_price)
}