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

class ProfileViewModel (private val profileRepository: ProfileRepository, private val token: String) : ViewModel() {


    private val _profileResponse = MutableStateFlow<ProfileResponse?>(null)
    val profileResponse: StateFlow<ProfileResponse?> = _profileResponse

    init {
        viewModelScope.launch {
            val result = profileRepository.getMyProfile(token)
            Log.d("TEST PROFILE", result.toString())
            _profileResponse.value = result
        }
    }


    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UcaHubApplication
                ProfileViewModel(app.profileRepository, app.getToken())
            }
        }
    }
}