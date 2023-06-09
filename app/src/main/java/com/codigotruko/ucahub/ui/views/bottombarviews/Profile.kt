package com.codigotruko.ucahub.ui.views.bottombarviews

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.presentation.author.LikesListViewModel
import com.codigotruko.ucahub.presentation.author.LikesListViewModelFactory
import com.codigotruko.ucahub.presentation.profile.MyProfileViewModel
import com.codigotruko.ucahub.presentation.profile.PublicationProfileListViewModel
import com.codigotruko.ucahub.presentation.profile.PublicationProfileListViewModelFactory
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.views.fragments.FloatingButton
import com.codigotruko.ucahub.ui.theme.mainBackground
import com.codigotruko.ucahub.ui.views.fragments.ImageUCAHUB
import com.codigotruko.ucahub.ui.views.overlapelements.EditProfileBox
import com.codigotruko.ucahub.ui.views.publication.PublicationItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ProfileView(navController: NavHostController){
    val app = LocalContext.current.applicationContext as UcaHubApplication
    val likesViewModelFactory = LikesListViewModelFactory(app.authorRepository, app.getToken())
    val likesViewModel: LikesListViewModel = viewModel(factory = likesViewModelFactory)

    val scope = CoroutineScope(Dispatchers.Main)

    val profileViewModel: MyProfileViewModel = viewModel(factory = MyProfileViewModel.Factory)
    val profile by profileViewModel.myProfileResponse.collectAsState()

    val valuesProgram = profile?.profile?.program

    var name = profile?.profile?.name ?: ""
    var username = profile?.profile?.username ?: ""
    var carnet = profile?.profile?.carnet ?: ""
    var description = profile?.profile?.description  ?: ""


    var faculty = "Facultad no asignada"
    var carrer = "Carrera no asignada"


    if (valuesProgram != null) {
        if (valuesProgram.isNotEmpty()){
            faculty = valuesProgram[0].faculty[0].name
            carrer = valuesProgram[0].name
        }
    }

    val publicationViewModelFactory = PublicationProfileListViewModelFactory(app.publicationRepository, app.getToken(), name )
    val publicationViewModel: PublicationProfileListViewModel = viewModel(factory = publicationViewModelFactory)
    val publications = publicationViewModel.publications.collectAsLazyPagingItems()

    val context = LocalContext.current


    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(profile) {
        if (profile != null) {
            isLoading.value = false
        }
    }

    LaunchedEffect(key1 = publications.loadState) {
        if(publications.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (publications.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }



    var showProfileBox by rememberSaveable{ mutableStateOf(false) }


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

                Button(onClick = { showProfileBox = true },
                    colors = ButtonDefaults.buttonColors(blueBackground),
                    shape = RoundedCornerShape(8.dp)) {
                    Icon(painter = painterResource(id = R.drawable.edit_icon),
                        tint = Color.White,
                        contentDescription = "Icono editar perfil.")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "Editar", fontSize = 18.sp)
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = username,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    TextProfileFragment(name = "Nombre", value = name)

                    TextProfileFragment(name = "Carnet", value = carnet)

                    TextProfileFragment(name = "Facultad", value = faculty)

                    TextProfileFragment(name = "Carrera", value = carrer)

                    TextProfileFragment(name = "Descripción", value = description)

                }

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp))
            }
            item {
                if(publications.loadState.refresh is LoadState.Loading){
                    CircularProgressIndicator(
                    )

                }
                else{
                    if (publications.itemCount == 0){
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                        ) {
                            Text(
                                text = "Mmm... Creo que vendria bien publicar algo.",
                                fontWeight = FontWeight.Medium,
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .align(alignment = Alignment.Center)
                            )
                        }
                    }
                }
            }
            items(publications){publication ->
                if(publications.loadState.refresh is LoadState.NotLoading) {
                    if (publication != null) {
                        PublicationItem(
                            publication = publication,
                            navController = navController,
                            publicationRefresh = {
                                publicationViewModel.refreshPublications()
                                publications.refresh()
                            },
                            onLiked = {
                                scope.launch {
                                    publicationViewModel.changeLikeState(publication._id)
                                }
                            },
                            comments = publicationViewModel.getComments(publication._id)

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
        profile?.profile?.let { EditProfileBox(showProfileBox, { showProfileBox = false }, profile = it, onConfirm = {
            profileViewModel.refreshProfile()

        }) }

        FloatingButton(false, "profile",
            action = {
                publicationViewModel.refreshPublications()
                publications.refresh()
            }
        )

    }







}

@Composable
fun TextProfileFragment(name: String?, value: String?){
    Column{
        Text(
            text = name.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value.toString(),
            fontSize = 20.sp
        )
    }
}



/*
private val _publications = MutableStateFlow(emptyFlow<PagingData<Publication>>())

val publications: StateFlow<Flow<PagingData<Publication>>> = _publications

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublicationListUi(publications: Flow<PagingData<Publication>>) {
    Scaffold(
        content = { it ->
            PublicationContent(
                publications = publications,
                onSelected = { publication -> Log.e("WordsContent",
                    "Selected: $publication") }
            )
        }
    )
}

@Composable
private fun PublicationContent(
    publications: Flow<PagingData<Publication>>,
    onSelected: (Publication) -> Unit,
) {
    // 1
    val items: LazyPagingItems<Publication> = publications.collectAsLazyPagingItems()
    LazyColumn {
        // 2
        items(items = items) { publication ->
            // 3
            if (publications != null) {
                PublicationColumnItem(
                    publication = publication
                ) { onSelected(publication) }
            }
        }
    }
}

@Composable
private fun PublicationColumnItem(
    publication: Publication,
    onClick: () -> Unit,
) {
    Row(                                              // 1
        modifier = Modifier.clickable { onClick() },    // 2
    ) {
        Text(
            modifier = Modifier.padding(16.dp),           // 3
            text = publication.name,                            // 4
        )
    }
}

*/