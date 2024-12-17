package com.deblock.application

import com.deblock.adapters.external.dto.request.RequestAirlineApi
import com.deblock.domain.Flight
import com.deblock.ports.AirlineExternalAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class GetFlightsAggregatedAndSortedByFare(private val flightsApiService: List<AirlineExternalAPI>) {

    suspend fun fetchFlights(request: RequestAirlineApi): Set<Flight> = withContext(Dispatchers.IO) {
        flightsApiService.map { service ->
            async {
                service.fetchFlights(request)
            }
        }.awaitAll().flatten().sortedBy { it.fare }.toSet()
    }
}