package com.codigotruko.ucahub.data.network.response

import com.codigotruko.ucahub.data.db.models.Author
import com.codigotruko.ucahub.data.db.models.Comment

class CommentListResponse (
    val count: String,
    var next: String?,
    var previous: String?,
    val results: List<Comment>
)