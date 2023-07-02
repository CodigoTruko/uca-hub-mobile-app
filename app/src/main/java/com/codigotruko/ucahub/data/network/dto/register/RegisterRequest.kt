package com.codigotruko.ucahub.data.network.dto.register

data class RegisterRequest(
    val name: String,
    val carnet: String,
    val username: String,
    val email: String,
    val password: String
)