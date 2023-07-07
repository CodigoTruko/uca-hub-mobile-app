package com.codigotruko.ucahub.ui.views.bottomnavbar

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.presentation.publication.PublicationListViewModel
import com.codigotruko.ucahub.ui.views.bottombarviews.CommunitiesView
import com.codigotruko.ucahub.ui.views.bottombarviews.MainFeedView
import com.codigotruko.ucahub.ui.views.bottombarviews.ProfileUserView
import com.codigotruko.ucahub.ui.views.bottombarviews.ProfileView
import com.codigotruko.ucahub.ui.views.bottombarviews.SearchView

// Clase que contiene la informacion de los elementos.
sealed class NavBarElements(val tittle: String, val route: String, @DrawableRes val icons: Int) {
    object Home : NavBarElements(
        tittle = "Home",
        route = "home_route",
        icons = R.drawable.home_icon
    )

    object Communities : NavBarElements(
        tittle = "Groups",
        route = "communities_route",
        icons = R.drawable.communities_icon
    )

    object Search : NavBarElements(
        tittle = "Search",
        route = "search_route",
        icons = R.drawable.search_icon
    )

    object Profile : NavBarElements(
        tittle = "Profile",
        route = "profile_route",
        icons = R.drawable.profile_icon
    )

}

// Rutas hacia la respectiva vista de cada icono.
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BottomNavHost(navHostController: NavHostController) {

    val publicationViewModel: PublicationListViewModel = viewModel(factory = PublicationListViewModel.Factory)
    val publications = publicationViewModel.publications.collectAsLazyPagingItems()

    NavHost(
        navController = navHostController,
        startDestination = NavBarElements.Home.route
    ) {
        composable(NavBarElements.Home.route) {
            MainFeedView(navHostController)
        }
        composable(NavBarElements.Communities.route) {
            CommunitiesView()
        }
        composable(NavBarElements.Search.route) {
            SearchView()
        }
        composable(NavBarElements.Profile.route) {
            ProfileView(
                navController = navHostController,
                userName = "RodAnd19",
                name = "Rodrigo Mena",
                carnet = "00078421",
                faculty = "Ingenieria y arquitectura",
                carrer = "Ingenieria Informatica",
                description = "AAAAAAAAAAAAAAAAAAAAA",
                userID = "1"
            )
        }
        // Ruta para publicaciones que no son mias.
        composable("anotherUser_profile/{username},{name},{description}") { backStackEntry ->

            var username = backStackEntry.arguments?.getString("username")
            var name = backStackEntry.arguments?.getString("name")
            var description = backStackEntry.arguments?.getString("description")

            ProfileUserView(username!!, name!!, description!!)
        }
    }
}
