package com.codigotruko.ucahub.data.network.service

import com.codigotruko.ucahub.data.db.models.Profile
import com.codigotruko.ucahub.data.db.models.ProfileResponse
import com.codigotruko.ucahub.data.network.response.AuthorListResponse
import com.codigotruko.ucahub.data.network.response.PublicationListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UcaHubApi {
    @GET("event/feed")
    suspend fun getFeedPublications(
        @Header("Authorization") token: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
        ): PublicationListResponse

    @GET("event/profile")
    suspend fun getUserPublications(
        @Header("Authorization") token: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): PublicationListResponse

    @GET("event/profile/{identifier}")
    suspend fun getProfilePublications(
        @Header("Authorization") token: String,
        @Path("identifier") identifier: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int
    ): PublicationListResponse

    @GET("user/profile")
    suspend fun getMyProfile(
        @Header("Authorization") token: String
    ): ProfileResponse

    @GET("user/identifier/{identifier}")
    suspend fun getUserProfile(
        @Header("Authorization") token: String,
        @Path("identifier") identifier: String

    ): ProfileResponse

    @GET("user/follows")
    suspend fun getUserFollows(
        @Header("Authorization") token: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
    ): AuthorListResponse

    @GET("user/followers")
    suspend fun getUserFollowers(
        @Header("Authorization") token: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
    ): AuthorListResponse

    @GET("user/search")
    suspend fun getUserSearch(
        @Header("Authorization") token: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int,
        @Query("keyword") keyword: String
    ): AuthorListResponse

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

    @POST("event")
    suspend fun createFeedPublication(
        @Header("Authorization") token: String,
        @Body requestBody: PublicationRequestBody
    )


    @DELETE("event/{id}")
    suspend fun deletePublication(
        @Header("Authorization") token: String,
        @Path("id") id: String
    )
}