package com.deblock.adapters.external.mapper

import com.deblock.adapters.external.dto.response.CrazyAirApiResponse
import com.deblock.domain.Flight

class CrazyAirMapper(private val response: CrazyAirApiResponse) : ApiResponseMapper {
    override fun mapToFlight(): Flight {
        return Flight(
            airline = response.airline,
            supplier = "CrazyAir",
            fare = response.price,
            departureAirportCode = response.departureAirportCode,
            destinationAirportCode = response.destinationAirportCode,
            departureDate = response.departureDate,
            arrivalDate = response.arrivalDate
        )
    }
}