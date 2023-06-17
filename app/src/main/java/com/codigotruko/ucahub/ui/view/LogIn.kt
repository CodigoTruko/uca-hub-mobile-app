package com.codigotruko.ucahub.ui.view

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import com.codigotruko.ucahub.ui.theme.mainBackground


@Composable
fun logInView(navController: NavHostController) {
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
                Row() {
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

            TxtField()

            Button(
                onClick = { navController.navigate("mainfeed") },
                colors = ButtonDefaults.buttonColors(blueBackground),
                modifier = Modifier
                    .padding(16.dp)
                    .width(300.dp)
            ) {
                Text(text = "Iniciar Sesión")
            }

            Text(text = "¿Primera vez en UCA-HUB? ¡Registrate aquí!",
                color = Color.Black.copy(alpha = 0.4f),
                modifier = Modifier.clickable {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TxtField() {
    val userInputValue = remember { mutableStateOf(TextFieldValue()) }
    val passwordInputValue = remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(value = userInputValue.value, onValueChange = {userInputValue.value = it},
            placeholder = { Text(text = "Usuario", color = Color.Black.copy(alpha = 0.5f)) },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(8.dp)
                .width(350.dp)
        )

        OutlinedTextField(value = passwordInputValue.value, onValueChange = {passwordInputValue.value = it},
            placeholder = { Text(text = "Contraseña", color = Color.Black.copy(alpha = 0.5f)) },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(8.dp)
                .width(350.dp)
        )
    }
}