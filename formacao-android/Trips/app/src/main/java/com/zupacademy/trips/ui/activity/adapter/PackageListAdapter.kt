package com.zupacademy.trips.ui.activity.adapter

import TravelPackageDAO
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.zupacademy.trips.R
import com.zupacademy.trips.model.TravelPackage
import com.zupacademy.trips.ui.activity.holder.ViewHolderPackageList
import com.zupacademy.trips.util.formatDayOnView
import com.zupacademy.trips.util.formatPriceOnView
import com.zupacademy.trips.util.getDrawableImage
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

class PackageListAdapter(
    private val context: Context,
    private val list: MutableList<TravelPackage>
) : BaseAdapter() {

    var packageDAO: TravelPackageDAO = TravelPackageDAO()

    override fun getCount(): Int {
        return list.count()
    }

    override fun getItem(position: Int): TravelPackage {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return 0L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView
            ?: LayoutInflater.from(context)
                .inflate(R.layout.item_travels_package, parent, false)

        val holder = ViewHolderPackageList(view)
        val travelPackage = packageDAO.getPackage(position)
        dataBindOnView(holder, travelPackage)


        return view
    }

    private fun dataBindOnView(holderPackageList: ViewHolderPackageList, travelPackage: TravelPackage) {
        holderPackageList.localNameView.text = travelPackage.local
        holderPackageList.daysView.text = formatDayOnView(travelPackage.days)
        holderPackageList.priceView.text = formatPriceOnView(travelPackage.price)
        val drawableImagePackage = getDrawableImage(context, travelPackage)
        holderPackageList.localImageView.setImageDrawable(drawableImagePackage)
    }
}
