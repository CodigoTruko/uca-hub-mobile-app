package com.codigotruko.ucahub.data.network.service

import com.codigotruko.ucahub.data.network.retrofit.RetrofitInstance
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
const val BASE_URL = "https://ucahub.tech/"

class UcaHubService {

    private val service: UcaHubApi = RetrofitInstance.getUcaHubService()

    suspend fun getPublications(token: String, limit: Int, offset: Int) =
        service.getPublications( "Bearer $token", offset, limit)
}