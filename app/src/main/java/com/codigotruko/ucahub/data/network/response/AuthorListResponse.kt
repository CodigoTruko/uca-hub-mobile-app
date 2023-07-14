package com.codigotruko.ucahub.data.network.response

import com.codigotruko.ucahub.data.db.models.Author

data class AuthorListResponse (
    val count: String,
    var next: String?,
    var previous: String?,
    val results: List<Author>
)