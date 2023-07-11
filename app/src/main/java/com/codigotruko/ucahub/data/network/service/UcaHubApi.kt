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
    data class ProfileRequestBody(val name: String, val carnet: String, val username: String, val email: String, val program: String, val description: String, val image: String )
    @PATCH("user")
    suspend fun changeProfileInfo(
        @Header("Authorization") token: String,
        @Body requestBody: ProfileRequestBody
    )

    @PATCH("event/{id}")
    suspend fun updatePublication(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body requestBody: PublicationRequestBody
    )

    @GET("event/likes/{id}")
    suspend fun getPublicationLikes(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    )

    @PATCH("event/like/{id}")
    suspend fun changeStatePublicationLike(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    )

    @GET("event/comment/{id}")
    suspend fun getPublicationComments(
        @Header("Authorization") token: String,
        @Path("id") id: String,
    )

    @POST("event/comment/{id}")
    suspend fun createPublicationComment(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body message: String
    )

    @DELETE("event/comment")
    suspend fun deletePublicationComment(
        @Header("Authorization") token: String,
        @Query("event") idEvent: String,
        @Query("comment") idComment: String
    )


}