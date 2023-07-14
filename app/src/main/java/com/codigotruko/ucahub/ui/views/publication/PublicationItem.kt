package com.codigotruko.ucahub.ui.views.publication

import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.data.db.models.Author
import com.codigotruko.ucahub.data.db.models.Comment
import com.codigotruko.ucahub.data.db.models.Publication
import com.codigotruko.ucahub.presentation.profile.MyProfileViewModel

import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import com.codigotruko.ucahub.ui.views.overlapelements.AddEditPublicationBox
import com.codigotruko.ucahub.ui.views.overlapelements.CommentsBox
import com.codigotruko.ucahub.ui.views.overlapelements.ConfirmBox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun PublicationItem(publication: Publication, navController: NavHostController, publicationRefresh: () -> Unit = {}, likes: Flow<PagingData<Author>>? = null, onLiked: ()->Unit, comments: Flow<PagingData<Comment>>) {

    val app = LocalContext.current.applicationContext as UcaHubApplication
    val scope = CoroutineScope(Dispatchers.Main)

    var show by rememberSaveable { mutableStateOf(false) }
    var showEditBox by rememberSaveable { mutableStateOf(false) }
    var showConfirmBox by rememberSaveable { mutableStateOf(false) }

    val profileViewModel: MyProfileViewModel = viewModel(factory = MyProfileViewModel.Factory)
    val profile by profileViewModel.myProfileResponse.collectAsState()

    val myProfileViewModel: MyProfileViewModel = viewModel(factory = MyProfileViewModel.Factory)
    val myProfile by myProfileViewModel.myProfileResponse.collectAsState()



    var userIdentifier = publication.author

    var author: String? = null

    if (userIdentifier != null){
        if (userIdentifier.isNotEmpty()) {
            author = userIdentifier[0].username
        }
    }

    var stateLike = publication.isLiked

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
                        if (author != null) {
                            if (myProfile?.profile?.username != author)
                                navController.navigate("anotherUser_profile/$author")
                            else
                                navController.navigate("profile_route")
                        }
                    }
            ) {
                Image(painter = painterResource(id = R.drawable.imagen_perfil_prueba),
                    contentDescription = "Imagen de perfil.",
                    modifier = Modifier
                        .height(80.dp)
                        .padding(16.dp))


                Text(text = author ?: "Author not found", fontSize = 25.sp, fontWeight = FontWeight.Medium)

                Spacer(modifier = Modifier.width(120.dp))

                if( author == profile?.profile?.username){
                    IconButton(
                        onClick = {
                            showEditBox = true
                        }
                    ) {
                        Icon(painter = painterResource(id = R.drawable.edit_icon), contentDescription = "Boton para editar publicación.")
                    }

                    IconButton(
                        onClick = {
                            showConfirmBox = true
                        }
                    ) {
                        Icon(painter = painterResource(id = R.drawable.delete_icon), contentDescription = "Boton para borrar publiación.")
                    }
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
                    .height(220.dp)
                    .padding(horizontal = 8.dp))

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(4.dp))

            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = publication.likes.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 16.dp))

                Log.d("LIKES:", likes?.collectAsLazyPagingItems()?.itemCount.toString())
                IconButton(
                    onClick = {

                        onLiked()
                        stateLike = true
                        publicationRefresh()

                    }
                ) {
                    Icon(
                        painter = painterResource(id = if (stateLike) R.drawable.red_heart else R.drawable.heart_icon),
                        contentDescription = if (stateLike) "Icono Unlike" else "Icono Like",
                        modifier = Modifier
                            .width(26.dp)
                            .height(40.dp)
                    )
                }

                Spacer(modifier = Modifier.width(50.dp))

                Text(text = publication.comments.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(horizontal = 16.dp))

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
    CommentsBox(
        show,
        { show = false },
        {
            show = false
            publicationRefresh()
        }, comments, publication._id)
    ConfirmBox(
        showConfirmBox,
        { showConfirmBox = false },
        {
            scope.launch {
                app.deletePublication(publication._id)
            }
            publicationRefresh()
        }
    )

    AddEditPublicationBox(showEditBox, { showEditBox = false }, false, publication, false, "feed",
        {
            publicationRefresh()
        })
}
