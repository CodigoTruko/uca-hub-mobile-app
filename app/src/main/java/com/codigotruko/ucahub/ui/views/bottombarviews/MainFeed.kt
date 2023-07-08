package com.codigotruko.ucahub.ui.views.bottombarviews

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.codigotruko.ucahub.presentation.ComunityItem
import com.codigotruko.ucahub.presentation.publication.PublicationListViewModel
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
                            myPublication = false
                        )
                    }
                }
            }
            item {
                if(publications.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
        }

        if (FloatingButton(false)) {
            LaunchedEffect(Unit) {
                    publicationViewModel.refreshPublications()
                    publications.refresh()
            }
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp))
    }
}