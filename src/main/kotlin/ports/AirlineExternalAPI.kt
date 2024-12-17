package com.deblock.ports

import com.deblock.adapters.external.dto.request.RequestAirlineApi
import com.deblock.domain.Flight

interface AirlineExternalAPI{
    fun fetchFlights(request: RequestAirlineApi) : Set<Flight>
}