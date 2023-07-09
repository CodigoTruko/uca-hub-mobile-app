package com.codigotruko.ucahub.presentation.login

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

class LoginViewModel(private val repository: CredentialsRepository) : ViewModel() {
    // Las de siempre.
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    // Para validar el log in.
    val _email: LiveData<String> = this.email
    val _password: LiveData<String> = this.password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable: LiveData<Boolean> = _loginEnable

    private val _status = MutableLiveData<LoginUiStatus>(LoginUiStatus.Resume)
    val status: MutableLiveData<LoginUiStatus>
        get() = _status

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            _status.postValue(
                when(val response = repository.login(email, password)){
                    is ApiResponse.Error -> LoginUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> LoginUiStatus.ErrorWithMessage(response.message)
                    is ApiResponse.Success -> LoginUiStatus.Success(response.data)
                }
            )
        }
    }

    fun onLogin() {
        if(!validateData()){
            _status.value = LoginUiStatus.ErrorWithMessage("Wrong information")
            return
        }
        login(this.email.value!!, this.password.value!!)
    }

    // Tambien servira para validar si esta habilitado el log in.
    private fun validateData(): Boolean {
        when {
            this.email.value.isNullOrEmpty() -> return false
            this.password.value.isNullOrEmpty() -> return false
        }
        return true
    }

    fun clearData() {
        this.email.value = ""
        this.password.value = ""
    }

    fun clearStatus() {
        _status.value = LoginUiStatus.Resume
    }

    fun onLoginChanged(email: String, password: String) {
        this.email.value = email
        this.password.value = password
        _loginEnable.value = isValidLogIn(email, password)
    }

    private fun isValidLogIn(email: String, password: String): Boolean = email.isNotEmpty() && password.isNotEmpty()

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as UcaHubApplication
                LoginViewModel(app.credentialsRepository)
            }
        }
    }
}