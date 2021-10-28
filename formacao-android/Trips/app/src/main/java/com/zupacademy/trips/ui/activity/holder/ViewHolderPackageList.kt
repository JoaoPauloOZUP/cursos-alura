package com.zupacademy.trips.ui.activity.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.zupacademy.trips.R

// Por que utilizar ViewHolder?
class ViewHolderPackageList(
    view: View
) {

    lateinit var localNameView: TextView
        private set
    lateinit var localImageView: ImageView
        private set
    lateinit var daysView: TextView
        private set
    lateinit var priceView: TextView
        private set

    init {
        localNameView = view.findViewById(R.id.list_package_special)
        localImageView = view.findViewById(R.id.item_travel_package_localimage)
        daysView = view.findViewById(R.id.item_travel_package_days)
        priceView = view.findViewById(R.id.item_travel_package_price)
    }
}