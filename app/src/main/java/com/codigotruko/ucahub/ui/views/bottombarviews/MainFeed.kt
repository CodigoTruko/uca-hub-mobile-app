package com.codigotruko.ucahub.ui.views.bottombarviews

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.codigotruko.ucahub.presentation.ComunityItem
import com.codigotruko.ucahub.presentation.publication.PublicationListViewModel
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import com.codigotruko.ucahub.ui.views.fragments.FloatingButton
import com.codigotruko.ucahub.ui.views.publication.PublicationItem
import kotlinx.coroutines.delay

@Composable
fun MainFeedView (navController: NavHostController ) {

    val publicationViewModel: PublicationListViewModel = viewModel(factory = PublicationListViewModel.Factory)
    val publications = publicationViewModel.feedPublications.collectAsLazyPagingItems()

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

    Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                ComunityItem(navController = navController)
            }
            items(publications){publication ->
                if(publications.loadState.refresh is LoadState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
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
            item {
                if(publications.itemCount == 0){

                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clickable {
                            navController.navigate("search_route")
                        },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = "¡Vaya!, un poco solo por aqui." +
                                        " Sigue a alguien para ver novedades",
                                fontWeight = FontWeight.Medium,
                                fontSize = 25.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }



        }

        FloatingButton(false, "feed",
            action = {
                publicationViewModel.refreshPublications()
                publications.refresh()
            }
        )


    }
}