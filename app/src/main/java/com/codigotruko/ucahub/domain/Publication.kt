package com.codigotruko.ucahub.domain

data class Publication (
    val id: Int,
    val name: String,
    val tagline: String,
    val firstBrewed: String,
    val description: String,
    val imageUrl: String?
)