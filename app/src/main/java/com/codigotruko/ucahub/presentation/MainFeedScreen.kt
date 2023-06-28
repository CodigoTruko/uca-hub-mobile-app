package com.codigotruko.ucahub.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.codigotruko.ucahub.domain.Publication
import com.codigotruko.ucahub.ui.view.publication.PublicationItem

@Composable
fun MainFeedScreen(
    publications: LazyPagingItems<Publication>

) {
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

    Box(modifier = Modifier.fillMaxSize()) {
        if(publications.loadState.refresh is LoadState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    ComunityItem()
                }
                items(publications) { publication ->
                    if (publication != null) {
                        PublicationItem(
                            publication = publication
                        )
                    }
                }
                item {
                    if(publications.loadState.append is LoadState.Loading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}