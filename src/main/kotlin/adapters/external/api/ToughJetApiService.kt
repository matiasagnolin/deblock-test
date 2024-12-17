package com.deblock.adapters.external.api

import com.deblock.adapters.external.dto.request.RequestAirlineApi
import com.deblock.adapters.external.dto.response.ToughAirApiResponse
import com.deblock.adapters.external.mapper.ToughJetMapper
import com.deblock.domain.Flight
import com.deblock.ports.AirlineExternalAPI
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json

class ToughJetApiService : AirlineExternalAPI {
    private val baseUrl = "https://api.crazyair.com/flights"

    override fun fetchFlights(request: RequestAirlineApi): Set<Flight> {
        val queryParams = listOf(
            "from" to request.origin,
            "to" to request.destination,
            "outboundDate" to request.departureDate,
            "inboundDate" to request.returnDate,
            "numberOfAdults" to request.passengerCount.toString()
        )

        val (_, _, result) = Fuel.get(baseUrl, queryParams)
            .responseString()

        when (result) {
            is Result.Success -> {
                val response = Json.decodeFromString<Set<ToughAirApiResponse>>(result.get())
                return response.map { item -> ToughJetMapper(item).mapToFlight() }.toSet()
            }

            is Result.Failure -> {
                throw RuntimeException("Failed to fetch flights: ${result.error.message}")
            }
        }
    }
}