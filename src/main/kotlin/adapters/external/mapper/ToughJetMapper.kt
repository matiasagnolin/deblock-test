package com.deblock.adapters.external.mapper

import com.deblock.adapters.external.dto.response.ToughAirApiResponse
import com.deblock.domain.Flight
import java.time.ZoneId

class ToughJetMapper(private val response: ToughAirApiResponse) : ApiResponseMapper {
    override fun mapToFlight(): Flight {
        return Flight(
            airline = response.carrier,
            supplier = "ToughJet",
            fare = response.calculateTotalPrice(),
            departureAirportCode = response.departureAirportName,
            destinationAirportCode = response.arrivalAirportName,
            departureDate = response.outboundDateTime.atZone(ZoneId.systemDefault()).toLocalDateTime(),
            arrivalDate = response.inboundDateTime.atZone(ZoneId.systemDefault()).toLocalDateTime()
        )
    }
}