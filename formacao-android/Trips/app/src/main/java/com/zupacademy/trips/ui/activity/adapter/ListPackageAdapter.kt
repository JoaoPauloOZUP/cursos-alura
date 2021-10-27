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
import com.zupacademy.trips.ui.activity.holder.ViewHolder
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

class ListPackageAdapter(
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

        val holder = ViewHolder(view)
        val travelPackage = packageDAO.getPackage(position)
        dataBindOnView(holder, travelPackage)


        return view
    }

    private fun dataBindOnView(holder: ViewHolder, travelPackage: TravelPackage) {
        holder.localNameView.text = travelPackage.local
        holder.daysView.text = formatDayOnView(travelPackage.days)
        holder.priceView.text = formatPriceOnView(travelPackage.price)
        val drawableImagePackage = getDrawableImage(travelPackage)
        holder.localImageView.setImageDrawable(drawableImagePackage)
    }

    private fun getDrawableImage(travelPackage: TravelPackage): Drawable {
        var resources: Resources = context.resources
        val identifierDrawable = resources.getIdentifier(travelPackage.image, "drawable", context.packageName)
        return resources.getDrawable(identifierDrawable)
    }

    private fun formatDayOnView(day: Int): String {
        return day
            .takeIf { it == 1 }
            ?.let { return@let "$it day" }
            ?: "$day days"
    }

    private fun formatPriceOnView(price: BigDecimal): String {
        val priceFormat = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
        return priceFormat.format(price).replace("R$", "R$ ")
    }
}
