package com.codigotruko.ucahub.data.mappers

import com.codigotruko.ucahub.data.local.PublicationEntity
import com.codigotruko.ucahub.data.remote.PublicationDto
import com.codigotruko.ucahub.domain.Publication

fun PublicationDto.toPublicationEntity(): PublicationEntity {
    return PublicationEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun PublicationEntity.toPublication(): Publication {
    return Publication(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = firstBrewed,
        imageUrl = imageUrl
    )
}