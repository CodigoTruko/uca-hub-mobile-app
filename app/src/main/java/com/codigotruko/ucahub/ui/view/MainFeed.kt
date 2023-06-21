package com.codigotruko.ucahub.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import com.codigotruko.ucahub.ui.theme.mainBackground

@Preview(showBackground = true)
@Composable
fun MainFeedView () {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(mainBackground)) {

        item {

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
                            .height(210.dp)
                            .padding(horizontal = 4.dp)) {
                            Image(painter = painterResource(R.drawable.imagen_prueba),
                                contentDescription = "Imagen de prueba.",
                                modifier = Modifier.fillMaxSize())
                        }

                        Card(modifier = Modifier
                            .width(140.dp)
                            .height(210.dp)
                            .padding(horizontal = 4.dp),
                            colors = CardDefaults.cardColors(blueBackground)) {

                            Text(text = "Entrar a una comunidad",
                                fontSize = 20.sp,
                                color = Color.White)

                        }

                    }
                }
            }

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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.imagen_perfil_prueba),
                            contentDescription = "Imagen de perfil.",
                            modifier = Modifier
                                .height(80.dp)
                                .padding(16.dp)
                        )

                        Text(
                            text = "Un trolo random",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Text(
                        text = "Descripción de pruebaaaaaaaaaaaaa aaaaaaaaaaaaa",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.publicacion_prueba),
                        contentDescription = "Publicación de prueba.",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                    
                    Row() {
                        // TODO: Agregar los iconos de la publicación.
                    }
                    
                }
            }


        }
    }
}


