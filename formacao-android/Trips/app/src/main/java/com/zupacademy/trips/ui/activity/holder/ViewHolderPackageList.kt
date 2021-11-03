package com.zupacademy.trips.ui.activity.holder

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zupacademy.trips.R
import com.zupacademy.trips.model.TravelPackage
import com.zupacademy.trips.util.formatDayOnView
import com.zupacademy.trips.util.formatPriceOnView
import com.zupacademy.trips.util.getDrawableImage

// Por que utilizar ViewHolder?
class ViewHolderPackageList(
    view: View
) : RecyclerView.ViewHolder(view) {
    private val localNameView: TextView = view.findViewById(R.id.list_package_special)
    private val localImageView: ImageView = view.findViewById(R.id.item_travel_package_localimage)
    private val daysView: TextView = view.findViewById(R.id.item_travel_package_days)
    private val priceView: TextView = view.findViewById(R.id.item_travel_package_price)

    fun bind(context: Context, travelPackage: TravelPackage) {
        localNameView.text = travelPackage.local
        daysView.text = formatDayOnView(travelPackage.days)
        priceView.text = formatPriceOnView(travelPackage.price)
        localImageView.setImageDrawable(getDrawableImage(context, travelPackage))
    }
}