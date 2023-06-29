package com.codigotruko.ucahub.data.remote

data class PublicationDto(
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val first_brewed: String,
    val image_url: String?
)