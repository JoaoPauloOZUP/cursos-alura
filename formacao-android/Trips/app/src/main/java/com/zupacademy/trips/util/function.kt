package com.zupacademy.trips.util

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import com.zupacademy.trips.model.TravelPackage
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun getDrawableImage(context: Context, travelPackage: TravelPackage): Drawable {
    var resources: Resources = context.resources
    val identifierDrawable = resources.getIdentifier(travelPackage.image, "drawable", context.packageName)
    return resources.getDrawable(identifierDrawable)
}

fun formatDayOnView(day: Int): String {
    return day
        .takeIf { it == 1 }
        ?.let { return@let "$it day" }
        ?: "$day days"
}

fun formatPriceOnView(price: BigDecimal): String {
    val priceFormat = DecimalFormat.getCurrencyInstance(Locale(ConstFormatMoney.LANGUAGE, ConstFormatMoney.COUNTRY))
    return priceFormat.format(price).replace("R$", ConstFormatMoney.FORMAT_MONEY)
}

private class ConstFormatMoney {
    companion object {
        const val LANGUAGE = "pt"
        const val COUNTRY = "br"
        const val FORMAT_MONEY = "R$ "
    }
}