package com.codigotruko.ucahub.data.network.service

import com.codigotruko.ucahub.data.network.retrofit.RetrofitInstance
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
const val BASE_URL = "https://ucahub.tech/"

class UcaHubService {

    val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2NGExYmUxYmNlZDM1ZGU0ZDlkOTQyYmQiLCJ1c2VybmFtZSI6IkZsYW4iLCJpYXQiOjE2ODgzMzU2NDEsImV4cCI6NTE2ODgzMzU2NDF9.Vu3U9mXp-mzTMYlaZcNaHYbmvydsGtoKqgxS1-5CSLI"

    private val service: UcaHubApi = RetrofitInstance.getUcaHubService()

    suspend fun getPublications(token: String, limit: Int, offset: Int) =
        service.getPublications( "Bearer $token", offset, limit)
}