package com.codigotruko.ucahub.ui.views.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import com.codigotruko.ucahub.ui.views.overlapelements.CommunitieInformationBox

@Composable
fun CommunitieCard () {

    var showCommunitieInfoBox by rememberSaveable() { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(darkWhiteBackground),
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.imagen_comunidad),
                contentDescription = "Imagen de comunidad.",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Row(horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 20.dp)
            ) {

                IconButton(onClick = { showCommunitieInfoBox = true }, modifier = Modifier.padding(horizontal = 4.dp)) {
                    Icon(painter = painterResource(id = R.drawable.information_icon),
                        tint = Color.White,
                        contentDescription = "Information icon.",
                        modifier = Modifier.fillMaxSize())
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.add_communitie_icon),
                        tint = Color.White,
                        contentDescription = "Add communitie icon.",
                        modifier = Modifier.fillMaxSize())
                }

            }

            Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxSize()) {
                // TODO : Adaptar variable para nombre de comunidad.
                Text(text = "Nombre de la comunidad",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 16.dp, horizontal = 22.dp))
            }

        }
    }
    CommunitieInformationBox(showCommunitieInfoBox, {showCommunitieInfoBox = false})
}

// Miembros que tendra cada comunidad.
@Composable
fun CommunitieMember() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.imagen_perfil_prueba),
            contentDescription = "Imagen de perfil",
            modifier = Modifier.width(25.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        // TODO : Adaptar una variable para el nombre de usuario.
        androidx.compose.material.Text(
            text = "Nombre de usuario",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}