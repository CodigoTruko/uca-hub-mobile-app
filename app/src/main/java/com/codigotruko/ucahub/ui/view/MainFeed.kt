package com.codigotruko.ucahub.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.codigotruko.ucahub.presentation.MainFeedScreen
import com.codigotruko.ucahub.presentation.publication.PublicationViewModel

@Composable
fun MainFeedView () {

    val viewModel = hiltViewModel<PublicationViewModel>()
    val publications = viewModel.publicationPagingFlow.collectAsLazyPagingItems()

    Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            MainFeedScreen(publications = publications)

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp))

    }
}