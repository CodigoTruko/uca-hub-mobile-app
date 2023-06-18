package com.codigotruko.ucahub.ui.view.bottomnavbar

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.codigotruko.ucahub.ui.view.TopBar

val listItems = listOf(
    NavBarElements.Home,
    NavBarElements.Communities,
    NavBarElements.Profile,
    NavBarElements.Menu
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaticItems(){
    val navController = rememberNavController()


    Scaffold(topBar = {
        TopBar()
    },
        bottomBar = {
        BottomNavBar(navController = navController, items = listItems)
        }) {
        BottomNavHost(navHostController = navController)
    }
}