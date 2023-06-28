package com.codigotruko.ucahub.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.repositories.PublicationRepository

class PublicationListViewModel(private val publicationRepository: PublicationRepository) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val publications = publicationRepository
        .getPublicationPage(10)


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UcaHubApplication
                PublicationListViewModel(app.pokemonRepository)
            }
        }
    }
}