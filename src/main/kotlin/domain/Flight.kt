package com.deblock.domain

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
class Flight(
    val airline: String,
    val supplier: String,
    val fare: Double,
    val departureAirportCode: String,
    val destinationAirportCode: String,
    @Contextual
    val departureDate: LocalDateTime,
    @Contextual
    val arrivalDate: LocalDateTime
)