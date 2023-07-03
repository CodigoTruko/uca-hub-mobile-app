package com.codigotruko.ucahub.data.network.service

import com.codigotruko.ucahub.data.network.response.PublicationListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
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
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
        ): PublicationListResponse

    @PATCH("user/follow/{identifier}")
    suspend fun changeFollowState(
        @Header("Authorization") token: String,
        @Path("identifier") identifier: String
    )

    @POST("event/profile")
    suspend fun createPublication(
        @Header("Authorization") token: String,
        @Header("title") title: String,
        @Header("description") description: String
    )

}