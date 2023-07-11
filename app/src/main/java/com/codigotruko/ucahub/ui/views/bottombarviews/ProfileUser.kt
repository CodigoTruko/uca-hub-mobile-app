package com.codigotruko.ucahub.ui.views.bottombarviews

import android.annotation.SuppressLint
import android.widget.Toast


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.presentation.author.FollowsViewModel
import com.codigotruko.ucahub.presentation.author.FollowsViewModelFactory
import com.codigotruko.ucahub.presentation.profile.ProfileViewModel
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.presentation.profile.ProfileViewModelFactory
import com.codigotruko.ucahub.presentation.profile.PublicationProfileListViewModel
import com.codigotruko.ucahub.presentation.profile.PublicationProfileListViewModelFactory
import com.codigotruko.ucahub.ui.theme.mainBackground
import com.codigotruko.ucahub.ui.views.fragments.ButtonNormalFragment
import com.codigotruko.ucahub.ui.views.fragments.ImageUCAHUB
import com.codigotruko.ucahub.ui.views.publication.PublicationItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@Composable
fun ProfileUserView(navController: NavHostController, userIdentifier: String){


    val app = LocalContext.current.applicationContext as UcaHubApplication



    val profileViewModelFactory = ProfileViewModelFactory(app.profileRepository, app.getToken(), userIdentifier)
    val profile: ProfileViewModel = viewModel(factory = profileViewModelFactory)
    val profileResponse by profile.profileResponse.collectAsState()

    val publicationViewModelFactory = PublicationProfileListViewModelFactory(app.publicationRepository, app.getToken(), userIdentifier)
    val publicationViewModel: PublicationProfileListViewModel = viewModel(factory = publicationViewModelFactory)
    val publications = publicationViewModel.publications.collectAsLazyPagingItems()

    val myFollowsViewModelFactory = FollowsViewModelFactory(app.authorRepository, app.getToken())
    val myFollowsViewModel: FollowsViewModel = viewModel(factory = myFollowsViewModelFactory)
    val myFollows = myFollowsViewModel.authors.collectAsLazyPagingItems()


    val dataProfile = profileResponse?.profile



    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(profileResponse) {
        if (profileResponse != null) {
            isLoading.value = false
        }
    }

    var faculty = "Facultad no asignada"
    var carrer = "Carrera no asignada"


    if(dataProfile?.program?.isNotEmpty() == true){
        faculty = dataProfile.program[0].faculty[0].name
        carrer = dataProfile.program[0].name

    }

    val username: String = dataProfile?.username ?: ""
    val name: String = dataProfile?.name ?: ""
    val description: String = dataProfile?.description ?: ""

    val carnet: String = dataProfile?.carnet ?: ""

    val scope = CoroutineScope(Dispatchers.Main)

    var stateFollow = ""

    var resultAuthor = myFollows.itemSnapshotList.find { author ->
        author?.username  == username
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = publications.loadState) {
        if(publications.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (publications.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    if(isLoading.value){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    else{
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = mainBackground)
        ){
            item{

                ImageUCAHUB()

                stateFollow = if ((resultAuthor != null) && (resultAuthor.username == username)) {
                    "Siguiendo"
                } else {
                    "Seguir"
                }
                ButtonNormalFragment(true, textValue = stateFollow) {
                    scope.launch {
                        if (username != null) {
                            app.changeStateFollow(username)
                            myFollowsViewModel.refreshAuthors()
                            myFollows.refresh()
                        }
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    if (username != null) {
                        Text(
                            text = username,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }

                    TextProfileFragment(name = "Nombre", value = name)

                    TextProfileFragment(name = "Carnet", value = carnet)

                    TextProfileFragment(name = "Facultad", value = faculty)

                    TextProfileFragment(name = "Carrera", value = carrer)

                    TextProfileFragment(name = "Descripción", value = description)

                }

                Button(
                    onClick = {/* TODO : Hacer on click para buscar en perfil. */},
                    colors = ButtonDefaults.buttonColors(blueBackground),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        tint = Color.White,
                        contentDescription = "Icono buscar."
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Buscar en el perfil")
                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp))
            }
            item {
                if(publications.loadState.refresh is LoadState.NotLoading && publications.itemCount == 0){
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                    ) {
                        Text(
                            text = "Sin publicaciones aún.",
                            fontWeight = FontWeight.Medium,
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .align(alignment = Alignment.Center)
                        )
                    }
                }
            }
            items(publications){publication ->
                if(publications.loadState.refresh is LoadState.Loading) {
                    CircularProgressIndicator(
                    )
                }
                else{
                    if (publication != null) {
                        PublicationItem(
                            publication = publication,
                            navController = navController,
                            publicationRefresh = {
                                publicationViewModel.refreshPublications()
                                publications.refresh()
                            }
                        )
                    }
                }

            }
            item {
                if(publications.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp))
            }
        }
    }

}
