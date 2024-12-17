package com.deblock.adapters.external.dto.response

import java.time.LocalDateTime

data class CrazyAirApiResponse(val airline: String,
                                val price: Double,
                                val cabinClass: CabinClass,
                                val departureAirportCode: String,
                                val destinationAirportCode: String,
                                val departureDate: LocalDateTime,
                                val arrivalDate: LocalDateTime ) : ResponseAirlineApi
