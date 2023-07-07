package com.codigotruko.ucahub.ui.views.publication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.data.db.models.Publication

import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import com.codigotruko.ucahub.ui.views.bottombarviews.ProfileUserView
import com.codigotruko.ucahub.ui.views.overlapelements.AddEditPublicationBox
import com.codigotruko.ucahub.ui.views.overlapelements.CommentsBox

@Composable
fun PublicationItem(publication: Publication, navController: NavHostController, myPublication: Boolean) {

    var show by rememberSaveable { mutableStateOf(false) }
    var showEditBox by rememberSaveable { mutableStateOf(false) }

    // Parametros para el perfil que se selecciona.
    var username = publication.author[0].username
    var name = publication.author[0].name
    var description = "LA JOURREIIII ESTA BIEN BUENA"

    Card(
        colors = CardDefaults.cardColors(darkWhiteBackground),
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()) {

            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("anotherUser_profile/$username,$name,$description")
                    }
            ) {
                Image(painter = painterResource(id = R.drawable.imagen_perfil_prueba),
                    contentDescription = "Imagen de perfil.",
                    modifier = Modifier
                        .height(80.dp)
                        .padding(16.dp))

                Text(text = publication.author[0].username, fontSize = 25.sp, fontWeight = FontWeight.Medium)

                Spacer(modifier = Modifier.width(60.dp))

                IconButton(onClick = { showEditBox = true }) {
                    Icon(painter = painterResource(id = R.drawable.edit_icon), contentDescription = "Boton para editar publicación.")
                }

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.delete_icon), contentDescription = "Boton para borrar publiación.")
                }
            }

            Text(text = publication.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(24.dp))

            Text(text = publication.description,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp))

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(24.dp))

            Image(painter = painterResource(id = R.drawable.publicacion_prueba) ,
                contentDescription = "Imagen de Publicación",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
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
    // Muestra la views de comentarios.
    CommentsBox(show, { show = false }, { show = false })
    AddEditPublicationBox(showEditBox, { showEditBox = false }, false, publication)
}
