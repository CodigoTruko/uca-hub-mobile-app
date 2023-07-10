package com.codigotruko.ucahub.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.codigotruko.ucahub.repository.ProfileRepository
import com.codigotruko.ucahub.data.db.models.ProfileResponse
import com.codigotruko.ucahub.data.db.models.Publication
import com.codigotruko.ucahub.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
class PublicationProfileListViewModel (private val publicationRepository: PublicationRepository, private val token: String, private val identifier: String) : ViewModel() {


    private val _publications = MutableStateFlow<Flow<PagingData<Publication>>>(emptyFlow())
    var publications: Flow<PagingData<Publication>> = _publications.flatMapLatest { it }


    init {
        viewModelScope.launch {
            val initialMyPublications = publicationRepository.getPublicationProfilePage(100, token, identifier)
            publications = initialMyPublications
        }
    }

}


@Suppress("UNCHECKED_CAST")
class PublicationProfileListViewModelFactory(
    private val publicationRepository: PublicationRepository,
    private val token: String,
    private val identifier: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PublicationProfileListViewModel(publicationRepository, token, identifier) as T
    }
}