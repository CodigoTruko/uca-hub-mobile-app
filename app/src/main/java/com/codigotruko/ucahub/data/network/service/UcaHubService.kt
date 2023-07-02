package com.codigotruko.ucahub.data.network.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UcaHubService {

    private val service: UcaHubApi = Retrofit.Builder()
        .baseUrl("https://ucahub.tech/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UcaHubApi::class.java)

    suspend fun getPublications(token: String, limit: Int, offset: Int) =
        service.getPublications(token, limit, offset)
}