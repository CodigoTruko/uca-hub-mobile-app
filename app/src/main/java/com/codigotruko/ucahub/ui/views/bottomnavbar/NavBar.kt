package com.codigotruko.ucahub.ui.views.bottomnavbar

import android.annotation.SuppressLint
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.views.menudesplegable.Destinos
import com.codigotruko.ucahub.ui.views.menudesplegable.Menu
import com.codigotruko.ucahub.ui.views.fragments.BottomNavBar
import com.codigotruko.ucahub.ui.views.fragments.TopBar

val listItems = listOf(
    NavBarElements.Home,
    NavBarElements.Communities,
    NavBarElements.Search,
    NavBarElements.Profile
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun StaticItems(){

    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val navigationItems = listOf(
        Destinos.Pantalla1,
        Destinos.Pantalla2,
        Destinos.Pantalla3,
        Destinos.Pantalla4
    )

    androidx.compose.material.Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState) },
        bottomBar = { BottomNavBar(navController = navController, items = listItems) },
        drawerContent = { Menu(menu_items = navigationItems, navController, scope, scaffoldState) },
        drawerGesturesEnabled = true,
        drawerBackgroundColor = blueBackground
    ) {
        BottomNavHost(navHostController = navController)
        }
}