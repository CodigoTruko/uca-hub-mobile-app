package com.codigotruko.ucahub.data.remote

import com.codigotruko.ucahub.data.remote.PublicationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UcaHubApi {
    @GET("publications")
    suspend fun getPublications(
        @Query("page") page: Int,
        @Query("per_page") pageCount: Int
    ): List<PublicationDto>

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }
}