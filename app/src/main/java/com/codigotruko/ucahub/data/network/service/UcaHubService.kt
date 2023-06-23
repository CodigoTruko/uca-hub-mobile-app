package com.codigotruko.ucahub.data.network.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UcaHubService {

    private val service: UcaHubApi = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UcaHubApi::class.java)

    suspend fun getPublications(limit: Int, offset: Int) =
        service.getPublications(limit, offset)
}