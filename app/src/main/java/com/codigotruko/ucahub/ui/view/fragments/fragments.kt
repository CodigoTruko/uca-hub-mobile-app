package com.codigotruko.ucahub.ui.view.fragments

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun txtFieldFragment(placeHolder: String): MutableState<TextFieldValue> {

    val inputValue = remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        value = inputValue.value,
        onValueChange = {
            run {
                inputValue.value = it
                Log.d("XDDDDDDD", inputValue.value.toString())
            }
        },
        placeholder = { Text(text = placeHolder, color = Color.Black.copy(alpha = 0.5f)) },
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(8.dp)
            .width(350.dp)
    )
    return inputValue
}

@Composable
fun ButtonNormalFragment(navController: NavHostController, textValue: String, destinationRoute: String = "", icon: String = ""){
    Button(
        onClick = { if(  destinationRoute.isNotEmpty() ){navController.navigate(destinationRoute)} },
        colors = ButtonDefaults.buttonColors(blueBackground),
        modifier = Modifier
            .padding(16.dp)
            .width(300.dp)
    ) {
        if(icon.isNotEmpty()){
            Icon(
                imageVector = iconFragment(icon = icon),
                contentDescription = "icono"
            )
        }

        Text(text = textValue)
    }
}

@Composable
fun ButtonGoogleFragment(textValue: String){
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

            Spacer(modifier = Modifier.size(16.dp))

            Text(text = textValue,  color = Color.Black)
        }
    }
}

@Composable
fun ImageUCAHUB(){
    Image(
        painter = painterResource(id = R.drawable.ucahub_icon),
        contentDescription = "Logo de la aplicación.",
        alignment = Alignment.Center,
        modifier = Modifier
            .height(200.dp)
            .padding(16.dp)
            .fillMaxWidth()
    )
}

@Composable
fun PublicationFragment(userName: String, description: String){
    Card(
        colors = CardDefaults.cardColors(darkWhiteBackground),
        modifier = Modifier
            .fillMaxWidth()
            .height(415.dp)
            .padding(6.dp)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()) {

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Image(painter = painterResource(id = R.drawable.imagen_perfil_prueba),
                    contentDescription = "Imagen de perfil.",
                    modifier = Modifier
                        .height(80.dp)
                        .padding(16.dp))

                Text(text = userName, fontSize = 25.sp, fontWeight = FontWeight.Medium)
            }

            Text(text = description,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp))

            Image(painter = painterResource(id = R.drawable.publicacion_prueba),
                contentDescription = "Imagen de Publicación",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp))
        }
    }
}


@Composable
fun ComunitysContainerFragment(){
    Card(
        colors = CardDefaults.cardColors(darkWhiteBackground),
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding(6.dp)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()) {

            Text(text = "Comunidades", fontSize = 20.sp, modifier = Modifier.padding(16.dp))

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)) {

                Card(modifier = Modifier
                    .width(140.dp)
                    .height(230.dp)
                    .padding(horizontal = 4.dp)) {
                    Image(painter = painterResource(R.drawable.imagen_prueba),
                        contentDescription = "Imagen de prueba.",
                        modifier = Modifier.fillMaxSize())
                }

                Card(modifier = Modifier
                    .width(140.dp)
                    .height(230.dp)
                    .padding(horizontal = 4.dp)) {
                    Image(painter = painterResource(R.drawable.imagen_prueba),
                        contentDescription = "Imagen de prueba.",
                        modifier = Modifier.fillMaxSize())
                }

            }
        }
    }
}

@Composable
fun HeaderFragment(){
    Card(colors = CardDefaults.cardColors(blueBackground),
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 0.dp)) {

            Text(text = "UCA HUB", color = Color.White, fontSize = 35.sp, fontWeight = FontWeight.Bold)
        }
    }
}
