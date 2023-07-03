package com.codigotruko.ucahub.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.data.network.ApiResponse
import com.codigotruko.ucahub.repository.CredentialsRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: CredentialsRepository) : ViewModel() {
    var name = MutableLiveData("")
    var carnet = MutableLiveData("")
    var username = MutableLiveData("")
    var email = MutableLiveData("")
    var password = MutableLiveData("")


    private val _status = MutableLiveData<RegisterUiStatus>(RegisterUiStatus.Resume)
    val status: LiveData<RegisterUiStatus>
        get() = _status

    private fun register(name: String, carnet: String, username: String, email: String, password: String) {
        viewModelScope.launch {
            _status.postValue(
                when(val response = repository.register(name, carnet, username, email, password)){
                    is ApiResponse.Error -> RegisterUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> RegisterUiStatus.ErrorWithMessage(response.message)
                    is ApiResponse.Success -> RegisterUiStatus.Success
                }
            )
        }

    }

    fun onRegister() {
        if(!validateData()){
            _status.value = RegisterUiStatus.ErrorWithMessage("Wrong information register")
            return
        }
        register(name.value!!, carnet.value!!, username.value!!, email.value!!, password.value!!)
    }

    private fun validateData(): Boolean {
        when {
            name.value.isNullOrEmpty() -> return false
            carnet.value.isNullOrEmpty() -> return false
            username.value.isNullOrEmpty() -> return false
            email.value.isNullOrEmpty() -> return false
            password.value.isNullOrEmpty() -> return false
        }
        return true
    }

    fun clearStatus() {
        _status.value = RegisterUiStatus.Resume
    }

    fun clearData() {
        name.value = ""
        carnet.value = ""
        username.value = ""
        email.value = ""
        password.value = ""
    }

    companion object {
        val factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as UcaHubApplication
                RegisterViewModel(app.credentialsRepository)
            }
        }
    }
}