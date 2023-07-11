package com.codigotruko.ucahub

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.codigotruko.ucahub.data.db.PublicationAppDatabase
import com.codigotruko.ucahub.data.network.retrofit.RetrofitInstance
import com.codigotruko.ucahub.data.network.service.UcaHubService
import com.codigotruko.ucahub.repository.AuthorRepository
import com.codigotruko.ucahub.repository.CredentialsRepository
import com.codigotruko.ucahub.repository.ProfileRepository
import com.codigotruko.ucahub.repository.PublicationRepository

class UcaHubApplication : Application(){


    private val database: PublicationAppDatabase by lazy {
        PublicationAppDatabase.getInstance(this)
    }
    private val ucaHubService by lazy {
        UcaHubService()
    }
    val publicationRepository by lazy {
        PublicationRepository(database, ucaHubService)
    }

    val authorRepository by lazy {
        AuthorRepository(database, ucaHubService)
    }

    val profileRepository by lazy {
        ProfileRepository(database, ucaHubService)
    }


    suspend fun changeStateFollow(identifier: String){
        ucaHubService.changeFollowState(getToken(), identifier)
    }


    suspend fun createPublication(title: String, description: String){
        ucaHubService.createPublication(getToken(), title, description)
    }

    suspend fun createFeedPublication(title: String, description: String){
        ucaHubService.createFeedPublication(getToken(), title, description)
    }

    suspend fun deletePublication(id: String){
        ucaHubService.deletePublication(getToken(), id)
    }
    suspend fun updatePublication(id: String, title: String, description: String){
        ucaHubService.updatePublication(getToken(), id, title, description)
    }
    suspend fun changeProfileInfo(name: String, carnet: String, username: String, email: String, program: String, description: String, image: String ){
        ucaHubService.changeProfileInfo(getToken(), name, carnet, username, email, program, description, image)
    }


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