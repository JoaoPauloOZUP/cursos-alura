package com.zupacademy.trips.model

import java.io.Serializable
import java.math.BigDecimal

class TravelPackage(
    val local: String,
    val image: String,
    val days: Int,
    val price: BigDecimal
) : Serializable {
}