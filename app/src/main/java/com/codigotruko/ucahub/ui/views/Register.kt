package com.codigotruko.ucahub.ui.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.presentation.login.LoginUiStatus
import com.codigotruko.ucahub.presentation.login.LoginViewModel
import com.codigotruko.ucahub.presentation.register.RegisterUiStatus
import com.codigotruko.ucahub.presentation.register.RegisterViewModel
import com.codigotruko.ucahub.ui.theme.mainBackground
import com.codigotruko.ucahub.ui.views.fragments.ButtonGoogleFragment
import com.codigotruko.ucahub.ui.views.fragments.ButtonNormalFragment
import com.codigotruko.ucahub.ui.views.fragments.ImageUCAHUB
import com.codigotruko.ucahub.ui.views.fragments.txtFieldFragment

@Composable
fun RegisterView(navController: NavHostController){

    val registerViewModel: RegisterViewModel = viewModel(factory = RegisterViewModel.factory)

    val status: RegisterUiStatus? by registerViewModel.status.observeAsState()

    status?.let { handleUiStatus(it, navController) }

    LazyColumn(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = mainBackground)
    ){
        item{

            ImageUCAHUB()

            Text(
                text = "Crear cuenta nueva",
                fontSize =32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
            )

            ButtonGoogleFragment(textValue = "Registrarse con Google")

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Or")
            }

            TxtFieldRegister()

            ButtonNormalFragment(
                navController = navController,
                textValue = "Crear cuenta",
                destinationRoute = "mainfeed",
                onclick = {
                    registerViewModel.onRegister()
                }
            )


            Text(text = "¿Ya posees una cuenta? ¡Inicia sesión aqui!",
                color = Color.Black.copy(alpha = 0.4f),
                modifier = Modifier
                    .padding(horizontal = 0.dp, vertical = 4.dp)
                    .clickable {
                        navController.navigate("login")
                }
            )

        }
    }
}


@Composable
fun TxtFieldRegister() {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        val registerViewModel: RegisterViewModel = viewModel()

        registerViewModel.name.value = txtFieldFragment(placeHolder = "Nombre completo" )
        registerViewModel.carnet.value = txtFieldFragment(placeHolder = "Carnet" )
        registerViewModel.username.value = txtFieldFragment(placeHolder = "Nombre de usuario" )
        registerViewModel.email.value = txtFieldFragment(placeHolder = "Correo eletronico" )
        registerViewModel.password.value = txtFieldFragment(placeHolder = "Contraseña" )
    }
}

@Composable
private fun handleUiStatus(status: RegisterUiStatus, navController: NavHostController){
    when(status){
        is RegisterUiStatus.Error -> {
            Toast.makeText(LocalContext.current, "An error has occurred", Toast.LENGTH_SHORT).show()
        }
        is RegisterUiStatus.ErrorWithMessage -> {
            Toast.makeText(LocalContext.current, status.message, Toast.LENGTH_SHORT).show()
        }
        is RegisterUiStatus.Success -> {
            val registerViewModel: RegisterViewModel = viewModel()
            registerViewModel.clearStatus()
            registerViewModel.clearData()
            navController.popBackStack()
        }
        else -> {}
    }
}