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

    lateinit var onItemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPackageList {
        val createdView = LayoutInflater.from(context)
            .inflate(R.layout.item_travels_package, parent, false)

        return ViewHolderPackageList(createdView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolderPackageList, position: Int) {
        list[position].let { travelPackage ->
            holder.bind(context, travelPackage)
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}