package com.deblock.adapters.external.dto.request

import kotlinx.serialization.Serializable
import java.time.LocalDate


@Serializable
data class RequestAirlineApi(val origin: String,
                             val destination: String,
                             val departureDate: String,
                             val returnDate: String,
                             val passengerCount: Int) {
    companion object {
        fun of(
            origin: String,
            destination: String,
            departureDate: LocalDate,
            returnDate: LocalDate,
            passengerCount: Int
        ): RequestAirlineApi {
            return RequestAirlineApi(
                origin = origin,
                destination = destination,
                departureDate = departureDate.toString(),
                returnDate = returnDate.toString(),
                passengerCount = passengerCount
            )
        }
    }
}
