package com.codigotruko.ucahub.ui.views.bottombarviews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.presentation.MainFeedScreen
import com.codigotruko.ucahub.presentation.publication.PublicationListViewModel
import com.codigotruko.ucahub.ui.views.fragments.FloatingButton

@Composable
fun MainFeedView (navController: NavHostController) {


    val publicationViewModel: PublicationListViewModel = viewModel(factory = PublicationListViewModel.Factory)

    val publications = publicationViewModel.publications.collectAsLazyPagingItems()

    Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        MainFeedScreen(publications = publications, navController = navController )

        FloatingButton()

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp))
    }
}