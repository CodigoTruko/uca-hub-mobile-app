package com.codigotruko.ucahub.ui.view.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.view.menudesplegable.currentRoute
import com.codigotruko.ucahub.ui.view.bottomnavbar.NavBarElements
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


// Barra superior que contiene el Menu y el Titulo.
@Composable
fun TopBar(
    scope: CoroutineScope, scaffoldState: ScaffoldState
) {
    TopAppBar(title = {
        Text(
            LocalContext.current.getString(R.string.app_name),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 64.dp)
        )
    }, modifier = Modifier.height(60.dp), navigationIcon = {
        IconButton(onClick = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.menu_icon),
                contentDescription = "Menu Icon"
            )
        }
    }, backgroundColor = blueBackground, contentColor = Color.White
    )
}


// Funcion que crea y setea los elementos en la BottomNavBar.
@Composable
fun BottomNavBar(navController: NavHostController, items: List<NavBarElements>) {

    val currentRoute = currentRoute(navController)

    BottomNavigation {
        items.forEach { screens ->
            BottomNavigationItem(
                modifier = Modifier.background(blueBackground),
                selected = currentRoute == screens.route,
                onClick = {
                    if (screens.route == "communities_route") {
                        navController.navigate(screens.route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    else {
                        navController.navigate(screens.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screens.icons),
                        contentDescription = screens.tittle
                    )
                },
                label = { Text(text = screens.tittle) },
                alwaysShowLabel = false
            )
        }
    }

}