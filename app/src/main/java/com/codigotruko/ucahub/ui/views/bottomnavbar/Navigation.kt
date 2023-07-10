package com.codigotruko.ucahub.ui.views.bottomnavbar

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.presentation.publication.PublicationListViewModel
import com.codigotruko.ucahub.ui.views.LogInView
import com.codigotruko.ucahub.ui.views.bottombarviews.CommunitieFeedView
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

    val app = LocalContext.current.applicationContext as UcaHubApplication

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
            SearchView(navHostController)
        }
        composable(NavBarElements.Profile.route) {
            ProfileView(navHostController)
        }
        // Ruta para publicaciones que no son mias.
        composable("anotherUser_profile/{userId}") { backStackEntry ->

            var userId = backStackEntry.arguments?.getString("userId")

            ProfileUserView(navHostController, userId!!)
        }
        composable("communities_feed") {
            CommunitieFeedView()
        }
    }
}
