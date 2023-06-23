package com.codigotruko.ucahub

import android.app.Application
import com.codigotruko.ucahub.data.db.PublicationAppDatabase
import com.codigotruko.ucahub.data.network.service.UcaHubService
import com.codigotruko.ucahub.repositories.PublicationRepository

class UcaHubApplication : Application() {
    private val database: PublicationAppDatabase by lazy {
        PublicationAppDatabase.getInstance(this)
    }
    private val ucaHubService by lazy {
        UcaHubService()
    }
    val pokemonRepository by lazy {
        PublicationRepository(database, ucaHubService)
    }
}