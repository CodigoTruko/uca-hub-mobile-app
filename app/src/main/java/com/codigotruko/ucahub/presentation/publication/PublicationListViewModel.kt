package com.codigotruko.ucahub.presentation.publication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.repository.PublicationRepository

class PublicationListViewModel(private val publicationRepository: PublicationRepository, private val token: String) : ViewModel() {


    @OptIn(ExperimentalPagingApi::class)
    val publications = publicationRepository
        .getPublicationPage(100, token)


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UcaHubApplication
                PublicationListViewModel(app.publicationRepository, app.getToken())
            }
        }
    }
}