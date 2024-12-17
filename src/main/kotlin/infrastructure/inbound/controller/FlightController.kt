package com.deblock.infrastructure.inbound.controller

import com.deblock.adapters.external.dto.request.RequestAirlineApi
import com.deblock.application.GetFlightsAggregatedAndSortedByFare
import com.deblock.domain.Flight
import com.deblock.infrastructure.inbound.controller.validation.IATACode
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Tag(name = "Flight Controller", description = "Operations pertaining to flights")
@RestController
@RequestMapping("/api")
@Validated
class FlightController(private val getFlightsAggregation: GetFlightsAggregatedAndSortedByFare) {
    @Operation(
        method = "searchFlights",
        operationId = "searchFlights",
        description = "Search flights for params",
        responses = [
            ApiResponse(
                description = "flights found",
                responseCode = "200",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
                )]
            ),
            ApiResponse(
                description = "bad request response",
                responseCode = "400"
            )]
    )
    @GetMapping("/flights")
    suspend fun searchFlights(
        @Parameter(
            description = "Origin airport IATA code",
            required = true
        ) @RequestParam(required = true) @IATACode origin: String,
        @Parameter(
            description = "Destination airport IATA code",
            required = true
        ) @RequestParam(required = true) @IATACode destination: String,
        @Parameter(
            description = "Departure date",
            required = true
        ) @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) departureDate: LocalDate,
        @Parameter(
            description = "Return date",
            required = true
        ) @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) returnDate: LocalDate,
        @Parameter(
            description = "Number of passengers",
            required = true
        ) @RequestParam(required = true) @Valid @Min(1) @Max(4) numberOfPassengers: Int
    ): ResponseEntity<Set<Flight>> {
        val request = RequestAirlineApi.of(origin, destination, departureDate, returnDate, numberOfPassengers)
        return ResponseEntity.ok(getFlightsAggregation.fetchFlights(request))
    }

}