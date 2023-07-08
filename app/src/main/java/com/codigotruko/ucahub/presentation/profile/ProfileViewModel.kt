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
            val result = if (identifier.isNotEmpty()) {
                profileRepository.getUserProfile(token, identifier)
            } else {
                profileRepository.getMyProfile(token)
            }
            _profileResponse.value = result
        }
    }

    suspend fun indents(aux: String){
        val result = profileRepository.getUserProfile(token, aux)
        _profileResponse.value = result
        Log.d("", result.profile.name)
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UcaHubApplication
                ProfileViewModel(app.profileRepository, app.getToken(), "")
            }
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