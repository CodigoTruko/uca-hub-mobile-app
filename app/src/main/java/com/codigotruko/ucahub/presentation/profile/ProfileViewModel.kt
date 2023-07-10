 package com.codigotruko.ucahub.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.repository.ProfileRepository
import com.codigotruko.ucahub.data.db.models.ProfileResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel (private val profileRepository: ProfileRepository, private val token: String, private val identifier: String) : ViewModel() {


    private val _profileResponse = MutableStateFlow<ProfileResponse?>(null)
    val profileResponse: StateFlow<ProfileResponse?> = _profileResponse

    init {
        viewModelScope.launch {
            _profileResponse.value = profileRepository.getUserProfile(token, identifier)
        }

    }

}


@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(
    private val profileRepository: ProfileRepository,
    private val token: String,
    private val identifier: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(profileRepository, token, identifier) as T
    }
}