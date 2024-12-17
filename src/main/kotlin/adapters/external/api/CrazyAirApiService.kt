package com.deblock.adapters.external.api

import com.deblock.adapters.external.dto.request.RequestAirlineApi
import com.deblock.adapters.external.dto.response.CrazyAirApiResponse
import com.deblock.adapters.external.mapper.CrazyAirMapper
import com.deblock.domain.Flight
import com.deblock.ports.AirlineExternalAPI
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import kotlinx.serialization.json.Json

class CrazyAirApiService : AirlineExternalAPI {

    private val baseUrl = "https://api.crazyair.com/flights"

    override fun fetchFlights(request: RequestAirlineApi): Set<Flight> {
        val queryParams = listOf(
            "origin" to request.origin,
            "destination" to request.destination,
            "departureDate" to request.departureDate,
            "returnDate" to request.returnDate,
            "passengerCount" to request.passengerCount.toString()
        )

        val (_, _, result) = Fuel.get(baseUrl, queryParams)
            .responseString()

        when (result) {
            is Result.Success -> {
                val response = Json.decodeFromString<Set<CrazyAirApiResponse>>(result.get())
                return response.map { item -> CrazyAirMapper(item).mapToFlight() }.toSet()
            }

            is Result.Failure -> {
                throw RuntimeException("Failed to fetch flights: ${result.error.message}")
            }
        }
    }
}

