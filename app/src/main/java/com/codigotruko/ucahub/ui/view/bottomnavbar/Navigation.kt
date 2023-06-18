package com.codigotruko.ucahub.ui.view.bottomnavbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.view.MainFeed

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

    object Profile : NavBarElements(
        tittle = "Profile",
        route = "profile_route",
        icons = R.drawable.profile_icon
    )

    object Menu : NavBarElements(
        tittle = "Menu",
        route = "menu_route",
        icons = R.drawable.menu_icon
    )
}

// Rutas hacia la respectiva vista de cada icono.
@Composable
fun BottomNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = NavBarElements.Home.route
    ) {
        composable(route = NavBarElements.Home.route) {
            MainFeed()
        }
        composable(route = NavBarElements.Communities.route) {
            MainFeed()
        }
        composable(route = NavBarElements.Profile.route) {
            MainFeed()
        }
        composable(route = NavBarElements.Menu.route) {
            MainFeed()
        }
    }
}

// Funcion que crea y setea los elementos en la BottomNavBar.
@Composable
fun BottomNavBar(navController: NavController, items: List<NavBarElements>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation {
        items.forEach { screens ->
            BottomNavigationItem(
                modifier = Modifier.background(blueBackground),
                selected = currentDestination?.route == screens.route,
                onClick = {
                    navController.navigate(screens.route){
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                icon = { Icon(
                     painter = painterResource(id = screens.icons),
                    contentDescription = null
                )
                },
                label = { Text(text = screens.tittle) },
                alwaysShowLabel = false
            )
        }
    }

}