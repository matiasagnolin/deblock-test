package com.deblock.adapters.external.mapper

import com.deblock.domain.Flight

interface ApiResponseMapper {
    fun mapToFlight(): Flight
}