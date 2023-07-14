package com.codigotruko.ucahub.repository


import com.codigotruko.ucahub.data.db.PublicationAppDatabase
import com.codigotruko.ucahub.data.db.models.ProfileResponse
import com.codigotruko.ucahub.data.network.service.UcaHubService

class ProfileRepository (
    private val database: PublicationAppDatabase,
    private val ucaHubService: UcaHubService
) {

    suspend fun getMyProfile(token: String): ProfileResponse {
        return ucaHubService.getMyProfile(token)
    }

    suspend fun getUserProfile(token: String, identifier: String): ProfileResponse {
        return ucaHubService.getUserProfile(token, identifier)
    }

}