package com.codigotruko.ucahub.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground


@Composable
fun ComunityItem(navController: NavHostController){

    Card(
        colors = CardDefaults.cardColors(darkWhiteBackground),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(6.dp)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()) {

            Text(text = "Comunidades", fontSize = 20.sp, modifier = Modifier.padding(12.dp))

            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)) {
                item {

                    Card(modifier = Modifier
                        .width(140.dp)
                        .height(210.dp)
                        .padding(horizontal = 4.dp)) {
                        Image(painter = painterResource(R.drawable.imagen_prueba),
                            contentDescription = "Imagen de prueba.",
                            modifier = Modifier.fillMaxSize())
                    }


                    Card(modifier = Modifier
                        .width(140.dp)
                        .height(210.dp)
                        .padding(horizontal = 4.dp)
                        .clickable { navController.navigate("communities_route") },
                        colors = CardDefaults.cardColors(blueBackground)) {

                        Text(text = "Entrar a una comunidad",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.5f)
                                .padding(16.dp))
                        Icon(painter = painterResource(id = R.drawable.add_icon),
                            contentDescription = "Agregar",
                            tint = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(16.dp))
                    }
                }
            }
        }


    }

}