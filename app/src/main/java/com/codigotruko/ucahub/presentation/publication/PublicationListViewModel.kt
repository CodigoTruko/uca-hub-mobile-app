package com.codigotruko.ucahub.presentation.publication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.data.db.models.Comment
import com.codigotruko.ucahub.data.db.models.Publication
import com.codigotruko.ucahub.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
class PublicationListViewModel(
    private val publicationRepository: PublicationRepository,
    private val token: String,
    private val userName: String,
) : ViewModel() {

    var feedPublications: Flow<PagingData<Publication>> =
        publicationRepository.getPublicationPage(100, token, userName)

    suspend fun changeLikeState(idPublication: String) {
        publicationRepository.changeLikeState(token, idPublication)
    }

    fun getComments(idPublication: String): Flow<PagingData<Comment>>{
        return publicationRepository.getCommentPage(100, token, idPublication)
    }

    suspend fun createComment(idPublication: String, message: String){
        publicationRepository.createComment(token, idPublication, message)
    }

    fun refreshPublications() {
        viewModelScope.launch {
            val refreshedFeedPublications = publicationRepository.getPublicationPage(100, token, userName)
            feedPublications = refreshedFeedPublications
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UcaHubApplication
                PublicationListViewModel(app.publicationRepository, app.getToken(), app.getUserName())
            }
        }
    }
}