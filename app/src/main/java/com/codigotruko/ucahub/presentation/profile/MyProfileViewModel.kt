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

class MyProfileViewModel (private val profileRepository: ProfileRepository, private val token: String) : ViewModel() {



    private val _myProfileResponse = MutableStateFlow<ProfileResponse?>(null)
    val myProfileResponse: StateFlow<ProfileResponse?> = _myProfileResponse

    init {
        viewModelScope.launch {
            _myProfileResponse.value = profileRepository.getMyProfile(token)

        }
    }

    fun refreshProfile(){
        viewModelScope.launch {
            _myProfileResponse.value = profileRepository.getMyProfile(token)

        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UcaHubApplication
                MyProfileViewModel(app.profileRepository, app.getToken())
            }
        }
    }
}
