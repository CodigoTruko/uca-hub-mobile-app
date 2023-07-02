package com.codigotruko.ucahub.data.network.response

import com.codigotruko.ucahub.data.db.models.Publication

data class PublicationListResponse(
    val count: String,
    var next: String?,
    var previous: String?,
    val results: List<Publication>
)
