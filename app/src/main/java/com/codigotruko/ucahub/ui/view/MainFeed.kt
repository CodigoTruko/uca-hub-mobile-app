package com.codigotruko.ucahub.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.codigotruko.ucahub.ui.view.fragments.PublicationFragment

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

            PublicationFragment(userName = "Username666", description = "Publicacion de prueba")
            PublicationFragment(userName = "Nexxtor", description = "Publicacion de prueba")
            PublicationFragment(userName = "Flan", description = "Publicacion de prueba")
            PublicationFragment(userName = "Mona", description = "Publicacion de prueba")

            Spacer(modifier = Modifier.fillMaxWidth().height(60.dp))

        }
    }
}


