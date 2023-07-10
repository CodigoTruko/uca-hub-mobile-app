package com.codigotruko.ucahub.ui.views.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import com.codigotruko.ucahub.ui.theme.disabledBlueBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun txtFieldFragment(placeHolder: String, visualT: VisualTransformation = VisualTransformation.None): String {

    val inputValue = remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        value = inputValue.value,
        onValueChange = {
                inputValue.value = it
        },
        visualTransformation = visualT,
        placeholder = { Text(text = placeHolder, color = Color.Black.copy(alpha = 0.5f)) },
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        maxLines = 1,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .fillMaxWidth()
    )
    return inputValue.value.text
}

@Composable
fun ButtonNormalFragment(
    buttonEnabled: Boolean,
    textValue: String,
    icon: String = "",
    padding: Dp = 16.dp,
    onclick: () -> Unit
){

    Button(
        onClick = onclick,
        colors = ButtonDefaults.buttonColors(
            containerColor =  blueBackground,
            disabledContainerColor = disabledBlueBackground,
            disabledContentColor = Color.White),
        enabled = buttonEnabled,
        modifier = Modifier
            .padding(horizontal = padding, vertical = padding/4)
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
