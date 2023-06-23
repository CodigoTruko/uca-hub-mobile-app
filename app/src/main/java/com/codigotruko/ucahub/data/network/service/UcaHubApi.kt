package com.codigotruko.ucahub.data.network.service

import com.codigotruko.ucahub.data.network.models.response.PublicationListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UcaHubApi {
    @GET("publication")
    suspend fun getPublications(
        @Query("limit") limit: Int,
        @Query("offset")
        offset: Int
    ): PublicationListResponse
}