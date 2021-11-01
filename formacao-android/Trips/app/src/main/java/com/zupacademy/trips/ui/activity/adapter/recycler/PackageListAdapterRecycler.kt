package com.zupacademy.trips.ui.activity.adapter.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zupacademy.trips.R

import com.zupacademy.trips.model.TravelPackage
import com.zupacademy.trips.ui.activity.holder.ViewHolderPackageList
import com.zupacademy.trips.util.formatDayOnView
import com.zupacademy.trips.util.formatPriceOnView
import com.zupacademy.trips.util.getDrawableImage

class PackageListAdapterRecycler(
    private val context: Context,
    private val list: MutableList<TravelPackage>
) : RecyclerView.Adapter<ViewHolderPackageList>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPackageList {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.item_travels_package, parent, false)

        return ViewHolderPackageList(createdView)
    }

    override fun onBindViewHolder(holder: ViewHolderPackageList, position: Int) {
        holder.localNameView.text = list[position].local
        holder.daysView.text = formatDayOnView(list[position].days)
        holder.priceView.text = formatPriceOnView(list[position].price)
        holder.localImageView.setImageDrawable(getDrawableImage(context, list[position]))
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}