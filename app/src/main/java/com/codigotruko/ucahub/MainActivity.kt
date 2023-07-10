package com.codigotruko.ucahub

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codigotruko.ucahub.presentation.login.LoginViewModel
import com.codigotruko.ucahub.ui.SessionManager
import com.codigotruko.ucahub.ui.views.LogInView
import com.codigotruko.ucahub.ui.views.bottomnavbar.StaticItems
import com.codigotruko.ucahub.ui.views.RegisterView
import kotlinx.coroutines.flow.MutableSharedFlow

class MainActivity : ComponentActivity() {

    val sessionManager: SessionManager by lazy {
        SessionManager(getSharedPreferences("session_prefs", Context.MODE_PRIVATE))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val saveSesion = remember { mutableStateOf(false) }

            // Este es el caso en el ya se inicia automaticamente la sesión luego de haber checkeado la box para recordar el inicio.
            if (sessionManager.isLoggedIn) {
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)

                        val savedUsername = sessionManager.preferences.getString(SessionManager.PREF_KEY_USERNAME, null)
                        val savedPassword = sessionManager.preferences.getString(SessionManager.PREF_KEY_PASSWORD, null)
                        savedUsername?.let { username ->
                            savedPassword?.let { password ->
                                loginViewModel.email.value = username
                                loginViewModel.password.value = password
                                loginViewModel.onLogin()
                                navController.navigate("mainfeed") {
                                    popUpTo("login") { inclusive = true }
                                }
                            }
                        }
                    }
                    composable("register"){
                        RegisterView(navController)
                    }
                    composable("mainfeed") {
                        StaticItems(sessionManager = sessionManager)
                    }
                }
            }
            // Este es el caso en el que el usuario si quiere guardar la información de inicio si checkea la box.
            else {
                NavHost(navController = navController, startDestination = "login") {
                    composable("login") {
                        LogInView(navController, saveSesion)
                    }
                    composable("register"){
                        RegisterView(navController)
                    }
                    composable("mainfeed") {

                        if (saveSesion.value) {
                            val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)

                            val username = loginViewModel.email.value
                            val password = loginViewModel.password.value
                            if (username != null && password != null) {
                                loginViewModel.onLogin()
                                sessionManager.saveCredentials(username, password)
                            }
                        }

                        StaticItems(sessionManager = sessionManager)
                    }
                }
            }
        }
    }
}