package com.codigotruko.ucahub.ui.views.overlapelements

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.data.db.models.Author
import com.codigotruko.ucahub.presentation.author.FollowsViewModel
import com.codigotruko.ucahub.presentation.author.FollowsViewModelFactory
import com.codigotruko.ucahub.presentation.publication.PublicationListViewModel
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment.Horizontal

@Composable
fun FollowingBox(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit, navController: NavHostController) {

    if (show) {

        val context = LocalContext.current
        val app = context.applicationContext as UcaHubApplication
        val scope = CoroutineScope(Dispatchers.Main)

        val myFollowsViewModelFactory = FollowsViewModelFactory(app.authorRepository, app.getToken())
        val myFollowsViewModel: FollowsViewModel = viewModel(factory = myFollowsViewModelFactory)
        val myFollows = myFollowsViewModel.authors.collectAsLazyPagingItems()

        val feedPublicationViewModel: PublicationListViewModel = viewModel(factory = PublicationListViewModel.Factory)
        val feedPublications = feedPublicationViewModel.feedPublications.collectAsLazyPagingItems()

        var isCircularProgressShown by remember { mutableStateOf(false) }

        LaunchedEffect(key1 = myFollows.loadState) {
            if(myFollows.loadState.refresh is LoadState.Error) {
                Toast.makeText(
                    context,
                    "Error: " + (myFollows.loadState.refresh as LoadState.Error).error.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        Dialog(onDismissRequest = { onDismiss() }) {
            Card(
                colors = CardDefaults.cardColors(darkWhiteBackground),
                modifier = Modifier
                    .height(600.dp)
                    .width(450.dp)
            ) {
                Text(
                    text = "Lista de personas que sigues",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )



                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        if(myFollows.loadState.refresh is LoadState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                        else{
                            if(myFollows.itemCount == 0){
                                Text(
                                    text = "No sigues a nadie.",
                                    textAlign = TextAlign.Center,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
                            }
                        }
                    }
                    items(myFollows){ author ->
                        if(myFollows.loadState.refresh is LoadState.NotLoading) {
                            if (author != null) {
                                cardAuthorFollow(
                                    navController = navController,
                                    author = author,
                                    action = {
                                        scope.launch {
                                            author.username.let { app.changeStateFollow(it) }
                                        }
                                        myFollowsViewModel.refreshAuthors()
                                        myFollows.refresh()
                                        feedPublicationViewModel.refreshPublications()
                                        feedPublications.refresh()
                                    },
                                    onDismiss = {
                                        onDismiss()
                                    }
                                )
                            }
                        }
                        else{

                        }

                    }
                    item {

                    }
                    item {
                        if(myFollows.loadState.append is LoadState.Loading) {
                            CircularProgressIndicator()
                        }
                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp))
                    }

                }
            }
        }
    }
}

@Composable
fun cardAuthorFollow(navController: NavHostController, author: Author, action: () -> Unit, onDismiss: () -> Unit){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .padding(horizontal = 16.dp)
            .clickable {
                onDismiss()
                navController.navigate("anotherUser_profile/${author.username}")
            }
    ) {

        Image(
            painter = painterResource(id = R.drawable.imagen_perfil_prueba),
            contentDescription = "Imagen de perfil",
            modifier = Modifier.width(25.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        // TODO : Adaptar una variable para el nombre de usuario.
        Text(
            text = author.username,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp
        )
        IconButton(onClick = { action() }) {
            androidx.compose.material3.Icon(
                painter = painterResource(id = R.drawable.delete_icon),
                contentDescription = "Boton eliminar",
                tint = Color.Unspecified,
                modifier = Modifier.width(20.dp)
            )
        }

    }


}