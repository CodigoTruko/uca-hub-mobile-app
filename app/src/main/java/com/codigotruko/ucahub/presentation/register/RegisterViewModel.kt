package com.codigotruko.ucahub.presentation.register

import android.util.Patterns
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
import java.util.regex.Pattern

class RegisterViewModel(private val repository: CredentialsRepository) : ViewModel() {
    // Las de siempre.
    val name = MutableLiveData("")
    val carnet = MutableLiveData("")
    val username = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    // Para validar Register.
    val _name: LiveData<String> = this.name
    val _carnet: LiveData<String> = this.carnet
    val _username: LiveData<String> = this.username
    val _email: LiveData<String> = this.email
    val _password: LiveData<String> = this.password

    private val _registerEnable = MutableLiveData<Boolean>()
    val registerEnable: LiveData<Boolean> = _registerEnable

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

    // Tambien servira para validar si esta habilitado el register.
    fun validateData(): Boolean {
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

    fun onRegisterChanged(name: String, carnet: String, username: String, email: String, password: String) {
        this.name.value = name
        this.carnet.value = carnet
        this.username.value = username
        this.email.value = email
        this.password.value = password
        _registerEnable.value = isValidRegister(name, carnet, username, email, password)
    }

    private fun isValidRegister(
        name: String,
        carnet: String,
        username: String,
        email: String,
        password: String
    ): Boolean = name.length > 0 && carnet.length > 0 && username.length > 0 &&
            Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 0

    companion object {
        val factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as UcaHubApplication
                RegisterViewModel(app.credentialsRepository)
            }
        }
    }
}