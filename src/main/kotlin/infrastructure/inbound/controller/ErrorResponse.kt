package com.deblock.infrastructure.inbound.controller

data class ErrorResponse(
    val status: Int,
    val message: String,
    val details: List<String>?
)