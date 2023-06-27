package com.codigotruko.ucahub.data.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.domain.Publication
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import com.codigotruko.ucahub.ui.view.overlayelements.CommentsBox

@Composable
fun PublicationItem(publication: Publication) {

    var show by rememberSaveable { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(darkWhiteBackground),
        modifier = Modifier
            .fillMaxWidth()
            .height(430.dp)
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

                Text(text = publication.name, fontSize = 25.sp, fontWeight = FontWeight.Medium)
            }

            Text(text = publication.name,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(24.dp))

            Image(painter = painterResource(id = R.drawable.publicacion_prueba),
                contentDescription = "Imagen de Publicación",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp))

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(4.dp))

            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.heart_icon),
                    contentDescription = "Icono Comentario",
                    modifier = Modifier
                        .width(26.dp)
                        .height(40.dp))
                Spacer(modifier = Modifier.width(50.dp))

                IconButton(onClick = { show = true }) {
                    Icon(painter = painterResource(id = R.drawable.comments_icon),
                        contentDescription = "Icono Comentario",
                        modifier = Modifier
                            .width(38.dp)
                            .height(40.dp))
                }

                Spacer(modifier = Modifier.width(50.dp))
                Icon(painter = painterResource(id = R.drawable.bookmark_icon),
                    contentDescription = "Icono BookMark",
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp))
            }
        }
    }
    // Muestra la view de comentarios.
    CommentsBox(show, { show = false }, { show = false })
}
