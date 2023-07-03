package com.codigotruko.ucahub.presentation.follow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.repository.PublicationRepository

class FollowStateViewModel(private val token: String, ucaHubApplication: UcaHubApplication) : ViewModel() {


    fun changeFollowState (identifier: String){

    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UcaHubApplication
                FollowStateViewModel(app.getToken(), app)
            }
        }
    }
}