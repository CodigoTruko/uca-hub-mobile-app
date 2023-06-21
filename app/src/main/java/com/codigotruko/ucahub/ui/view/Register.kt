package com.codigotruko.ucahub.ui.view

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.ui.theme.mainBackground
import com.codigotruko.ucahub.ui.view.fragments.ButtonGoogleFragment
import com.codigotruko.ucahub.ui.view.fragments.ButtonNormalFragment
import com.codigotruko.ucahub.ui.view.fragments.ImageUCAHUB
import com.codigotruko.ucahub.ui.view.fragments.txtFieldFragment

@Composable
fun RegisterView(navController: NavHostController){
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
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
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

            ButtonNormalFragment(navController = navController, textValue = "Crear cuenta", destinationRoute = "mainfeed")


            Text(text = "¿Primera vez en UCA-HUB? ¡Registrate aquí!",
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
fun TxtFieldRegister() {

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        txtFieldFragment(placeHolder = "Nombre de usuario" )
        txtFieldFragment(placeHolder = "Carnet" )
        txtFieldFragment(placeHolder = "Correo eletronico" )
        txtFieldFragment(placeHolder = "Contraseña" )
    }
}




