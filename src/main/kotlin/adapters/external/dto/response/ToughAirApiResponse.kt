package com.deblock.adapters.external.dto.response

import java.time.Instant

class ToughAirApiResponse(
    val carrier: String,
    val basePrice: Double,
    val tax: Double,
    val discount: Double,
    val departureAirportName: String,
    val arrivalAirportName: String,
    val inboundDateTime: Instant,
    val outboundDateTime: Instant
) : ResponseAirlineApi {
    fun calculateTotalPrice(): Double {
        val discountAmount = basePrice * (discount / 100)
        return (basePrice + tax) - discountAmount
    }
}
