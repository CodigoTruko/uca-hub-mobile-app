package com.codigotruko.ucahub.data.network.service

import com.codigotruko.ucahub.data.network.response.LikeResponse
import com.codigotruko.ucahub.data.network.retrofit.RetrofitInstance

const val BASE_URL = "https://ucahub.tech/"

class UcaHubService {

    private val service: UcaHubApi = RetrofitInstance.getUcaHubService()

    suspend fun getFeedPublications(token: String, limit: Int, offset: Int) =
        service.getFeedPublications( "Bearer $token", offset, limit)
    suspend fun getUserPublications(token: String, limit: Int, offset: Int) =
        service.getUserPublications( "Bearer $token", offset, limit)

    suspend fun getProfilePublications(token: String, identifier: String, limit: Int, offset: Int) =
        service.getProfilePublications( "Bearer $token", identifier, offset, limit )

    suspend fun getMyProfile(token: String) =
        service.getMyProfile("Bearer $token")

    suspend fun getUserProfile(token: String, identifier: String) =
        service.getUserProfile("Bearer $token", identifier)

    suspend fun createPublication(token: String, title: String, description: String) =
        service.createPublication("Bearer $token", UcaHubApi.PublicationRequestBody(title, description))
    suspend fun createFeedPublication(token: String, title: String, description: String) =
        service.createFeedPublication("Bearer $token", UcaHubApi.PublicationRequestBody(title, description))

    suspend fun changeProfileInfo(token: String, name: String, carnet: String, username: String, email: String, program: String, description: String, image: String ) =
        service.changeProfileInfo("Bearer $token", UcaHubApi.ProfileRequestBody(name, carnet, username, email, program, description, image))
    suspend fun deletePublication(token: String, id: String) =
        service.deletePublication("Bearer $token", id)

    suspend fun updatePublication(token: String, id: String, title: String, description: String) =
        service.updatePublication("Bearer $token", id, UcaHubApi.PublicationRequestBody(title, description))

    suspend fun changeFollowState(token: String, identifier: String) =
        service.changeFollowState("Bearer $token", identifier)


    suspend fun getUserSearch(token: String, limit: Int, offset: Int, text: String) =
        service.getUserSearch("Bearer $token", offset, limit, text)

    suspend fun getUserFollowers(token: String, limit: Int, offset: Int) =
        service.getUserFollowers("Bearer $token", offset, limit)

    suspend fun getUserFollows(token: String, limit: Int, offset: Int) =
        service.getUserFollows("Bearer $token", offset, limit)

    suspend fun getPublicationLikes(token: String, id: String, limit: Int, offset: Int) =
        service.getPublicationLikes("Bearer $token", id, offset, limit)
    suspend fun changeStatePublicationLike(token: String, id: String): LikeResponse {
        return service.changeStatePublicationLike("Bearer $token", id)

    }

    suspend fun getPublicationComments(token: String, id: String, limit: Int, offset: Int) =
        service.getPublicationComments("Bearer $token", id, offset, limit)

    suspend fun createPublicationComment(token: String, id: String, message: String) =
        service.createPublicationComment("Bearer $token", id, UcaHubApi.messageBody(message))

}