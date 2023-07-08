package com.codigotruko.ucahub.presentation.publication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.data.db.models.Publication
import com.codigotruko.ucahub.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
class PublicationListViewModel(private val publicationRepository: PublicationRepository, private val token: String) : ViewModel() {

    private val _feedPublications = MutableStateFlow<Flow<PagingData<Publication>>>(emptyFlow())
    var feedPublications: Flow<PagingData<Publication>> = _feedPublications.flatMapLatest { it }

    private val _myPublications = MutableStateFlow<Flow<PagingData<Publication>>>(emptyFlow())
    var myPublications: Flow<PagingData<Publication>> = _myPublications.flatMapLatest { it }

    init {
        viewModelScope.launch {
            val initialMyPublications = publicationRepository.getPublicationPage(100, token, "myProfile")
            myPublications = initialMyPublications
        }
        viewModelScope.launch {
            val initialFeedPublications = publicationRepository.getPublicationPage(100, token, "feed")
            feedPublications = initialFeedPublications
        }
    }

    fun refreshPublications() {
        viewModelScope.launch {
            val refreshedFeedPublications = publicationRepository.getPublicationPage(100, token, "feed")
            feedPublications  = refreshedFeedPublications

            val refreshedMyPublications = publicationRepository.getPublicationPage(100, token, "myProfile")
            myPublications = refreshedMyPublications
        }
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UcaHubApplication
                PublicationListViewModel(app.publicationRepository, app.getToken())
            }
        }
    }
}