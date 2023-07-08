package com.codigotruko.ucahub.data.network.service

import android.util.Log
import com.codigotruko.ucahub.data.db.models.Profile
import com.codigotruko.ucahub.data.network.retrofit.RetrofitInstance

const val BASE_URL = "https://ucahub.tech/"

class UcaHubService {

    private val service: UcaHubApi = RetrofitInstance.getUcaHubService()

    suspend fun getDFeedPublications(token: String, limit: Int, offset: Int) =
        service.getFeedPublications( "Bearer $token", offset, limit)
    suspend fun getUserPublications(token: String, limit: Int, offset: Int) =
        service.getUserPublications( "Bearer $token", offset, limit)

    suspend fun getMyProfile(token: String) =
        service.getMyProfile("Bearer $token")

    suspend fun getUserProfile(token: String, identifier: String) =
        service.getUserProfile("Bearer $token", identifier)

    suspend fun createPublication(token: String, title: String, description: String) =
        service.createPublication("Bearer $token", UcaHubApi.PublicationRequestBody(title, description))

    suspend fun changeFollowState(token: String, identifier: String) =
        service.changeFollowState("Bearer $token", identifier)
}