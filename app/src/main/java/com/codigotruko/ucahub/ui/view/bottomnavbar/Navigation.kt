package com.codigotruko.ucahub.ui.view.bottomnavbar

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BackdropScaffoldState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.view.CommunitiesView
import com.codigotruko.ucahub.ui.view.MainFeedView
import com.codigotruko.ucahub.ui.view.profileView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BottomNavHost(navHostController: NavHostController, scope : CoroutineScope, scaffoldState: ScaffoldState) {
    NavHost(
        navController = navHostController,
        startDestination = NavBarElements.Home.route
    ) {
        composable(route = NavBarElements.Home.route) {
            MainFeedView()
        }
        composable(route = NavBarElements.Communities.route) {
            CommunitiesView(navController = navHostController)
        }
        composable(route = NavBarElements.Profile.route) {
            profileView(navController = navHostController)
        }
        composable(route = NavBarElements.Menu.route) {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        }
    }
}

@Composable
fun TopBar() {
    Card(colors = CardDefaults.cardColors(blueBackground),
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)) {

        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 0.dp)) {

            Text(text = "UCA HUB", color = Color.White, fontSize = 35.sp, fontWeight = FontWeight.Bold)
            Icon(painter = painterResource(id = R.drawable.search_icon), modifier = Modifier
                .width(45.dp)
                .height(100.dp), contentDescription = null, tint = Color.White)
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