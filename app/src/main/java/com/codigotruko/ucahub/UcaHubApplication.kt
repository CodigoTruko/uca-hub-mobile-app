package com.codigotruko.ucahub

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.codigotruko.ucahub.data.network.retrofit.RetrofitInstance
import com.codigotruko.ucahub.repository.CredentialsRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UcaHubApplication : Application(){

    private val prefs: SharedPreferences by lazy {
        getSharedPreferences("Retrofit", Context.MODE_PRIVATE)
    }

    private fun getAPIService() = with(RetrofitInstance){
        setToken(getToken())
        getLoginService()
    }

    fun getToken(): String = prefs.getString(USER_TOKEN, "")!!

    val credentialsRepository: CredentialsRepository by lazy {
        CredentialsRepository(getAPIService())
    }

    fun saveAuthToken(token: String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    companion object {
        const val USER_TOKEN = "user_token"
    }
}