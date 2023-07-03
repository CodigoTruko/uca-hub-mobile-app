package com.codigotruko.ucahub.ui.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.presentation.login.LoginUiStatus
import com.codigotruko.ucahub.presentation.login.LoginViewModel
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import com.codigotruko.ucahub.ui.theme.mainBackground


import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.ui.views.fragments.ButtonNormalFragment
import com.codigotruko.ucahub.ui.views.fragments.txtFieldFragment


@Composable
fun LogInView(navController: NavHostController) {

    val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)
    val app = LocalContext.current.applicationContext as UcaHubApplication

    val status: LoginUiStatus? by loginViewModel.status.observeAsState()

    status?.let { HandleUiStatus(it, app, navController) }

    LazyColumn(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = mainBackground)
    ) {
        item {

            Image(
                painter = painterResource(id = R.drawable.ucahub_icon),
                contentDescription = "Logo de la aplicación.",
                alignment = Alignment.Center,
                modifier = Modifier
                    .height(200.dp)
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Iniciar Sesión",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(darkWhiteBackground),
                modifier = Modifier
                    .padding(16.dp)
                    .width(300.dp)
            ) {
                Row{
                    Image(painter = painterResource(id = R.drawable.google_icon),
                        contentDescription = "Icono de Google.",
                        modifier = Modifier
                            .height(20.dp)
                            .width(20.dp))

                    // Sirve para dar espaciado entre elementos.
                    Spacer(modifier = Modifier.size(16.dp))

                    Text(text = "Sing In With Google",  color = Color.Black)
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Or")
            }

            TxtFieldLogIn()

            ButtonNormalFragment(
                navController = navController,
                textValue = "Iniciar Sesión",
                destinationRoute = "mainfeed",
                onclick = {
                    loginViewModel.onLogin()
                }
            )

            Text(text = "¿Primera vez en UCA-HUB? ¡Registrate aquí!",
                color = Color.Black.copy(alpha = 0.4f),
                modifier = Modifier.clickable {
                    navController.navigate("register")
                }
            )

            Text(text = "¿Olvidaste tu contraseña?",
                color = Color.Black.copy(alpha = 0.4f),
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 16.dp)
                    .clickable {
                    }
            )
        }
    }


}


@Composable
fun TxtFieldLogIn() {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        val loginViewModel: LoginViewModel = viewModel()

        loginViewModel.email.value = txtFieldFragment(placeHolder = "Usuario" )
        loginViewModel.password.value = txtFieldFragment(placeHolder = "Contraseña", PasswordVisualTransformation() )
    }
}
@Composable
private fun HandleUiStatus(status: LoginUiStatus, app: UcaHubApplication, navController: NavHostController) {
    Log.d("XD", "NAV")

    when (status) {
        is LoginUiStatus.Error -> {
            Toast.makeText(LocalContext.current, "An error has occurred", Toast.LENGTH_SHORT).show()
            Log.d("XD", "NAV1")

        }
        is LoginUiStatus.ErrorWithMessage -> {
            Toast.makeText(LocalContext.current, status.message, Toast.LENGTH_SHORT).show()
            Log.d("XD", "NAV2")

        }
        is LoginUiStatus.Success -> {
            val loginViewModel: LoginViewModel = viewModel()
            loginViewModel.clearStatus()
            loginViewModel.clearData()
            app.saveAuthToken(status.token)
            Log.d("TOKEN", app.getToken())
            navController.navigate("mainfeed")
        }

        else -> {}
    }
}