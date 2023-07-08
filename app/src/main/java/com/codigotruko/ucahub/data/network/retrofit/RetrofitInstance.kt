package com.codigotruko.ucahub.data.network.retrofit

import android.util.Log
import com.codigotruko.ucahub.data.network.service.AuthService
import com.codigotruko.ucahub.data.network.service.UcaHubApi
import com.codigotruko.ucahub.data.network.service.UcaHubService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://ucahub.tech/"

object RetrofitInstance {

    private var token = ""

    fun setToken(token: String){
        this.token = token
    }

    private val authInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .build()
        chain.proceed(request)
    }

    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d(
            "HTTP Request",
            message
        )
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    fun getUcaHubService(): UcaHubApi {
        return retrofit.create(UcaHubApi::class.java)
    }


    fun getLoginService(): AuthService{
        return retrofit.create(AuthService::class.java)
    }


}
