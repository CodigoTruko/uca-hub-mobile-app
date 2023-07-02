package com.codigotruko.ucahub.data.network.service

import com.codigotruko.ucahub.data.network.response.PublicationListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UcaHubApi {

    /*@GET("pokemon")
    suspend fun getPublications(
        @Query("limit") limit: Int,
        @Query("offset")
        offset: Int
    ): PublicationListResponse
    */

    @GET("event/feed")
    suspend fun getPublications(
        @Header("Authorization") token: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
        ): PublicationListResponse
}