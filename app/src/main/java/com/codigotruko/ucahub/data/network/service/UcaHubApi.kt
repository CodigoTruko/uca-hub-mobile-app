package com.codigotruko.ucahub.data.network.service

import com.codigotruko.ucahub.data.network.response.PublicationListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UcaHubApi {
    @GET("event/feed")
    suspend fun getPublications(
        @Header("Authorization") token: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
        ): PublicationListResponse

    @GET("event/profile")
    suspend fun getMyPublications(
        @Header("Authorization") token: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    )

    @GET("user/follows")
    suspend fun getMyFollows(
        @Header("Authorization") token: String,
    )

    @GET("user/follows")
    suspend fun getMyFollowers(
        @Header("Authorization") token: String,
    )

    @PATCH("user/follow/{identifier}")
    suspend fun changeFollowState(
        @Header("Authorization") token: String,
        @Path("identifier") identifier: String
    )

    data class PublicationRequestBody(val title: String, val description: String)
    @POST("event/profile")
    suspend fun createPublication(
        @Header("Authorization") token: String,
        @Body requestBody: PublicationRequestBody
    )
}