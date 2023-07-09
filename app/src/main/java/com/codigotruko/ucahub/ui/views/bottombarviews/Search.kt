package com.codigotruko.ucahub.ui.views.bottombarviews

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground

@Composable
fun SearchView (navController: NavHostController) {

    val inputValue = remember { mutableStateOf(TextFieldValue()) }

    Column {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            elevation = 8.dp
        ) {
            Row (modifier = Modifier.fillMaxWidth()) {
                TextField(value = inputValue.value, onValueChange = { inputValue.value = it },
                    label = { Text(text = "Buscar") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    leadingIcon = { Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = "Icono Buscar"
                    ) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp))
            }
        }
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {

            item {

                SearchCard(navController, inputValue)
                SearchCard(navController, inputValue)

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp))
            }
        }
    }

}
@Composable
fun SearchCard(navController: NavHostController, inputValue: MutableState<TextFieldValue>) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .clickable {
            navController.navigate("anotherUser_profile/${inputValue.value}")
        },
        backgroundColor = darkWhiteBackground,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.imagen_perfil_prueba),
                contentDescription = "Imagen de perfil",
                modifier = Modifier.width(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Prueba",
                fontWeight = FontWeight.Medium,
                fontSize = 25.sp
            )
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}