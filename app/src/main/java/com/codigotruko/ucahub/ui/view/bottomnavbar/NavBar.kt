package com.codigotruko.ucahub.ui.view.bottomnavbar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.lightBlueBackground
import com.codigotruko.ucahub.ui.view.MenuDesplegable.Destinos
import com.codigotruko.ucahub.ui.view.fragments.BottomNavBar
import com.codigotruko.ucahub.ui.view.fragments.Menu
import com.codigotruko.ucahub.ui.view.fragments.TopBar

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
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(100),
                containerColor = lightBlueBackground) {

                Icon(painter = painterResource(
                    id = R.drawable.add_icon),
                    modifier = Modifier.height(50.dp),
                    contentDescription = null, tint = Color.White
                )
            }
        },
        bottomBar = { BottomNavBar(navController = navController, items = listItems) },
        drawerContent = { Menu(menu_items = navigationItems, navController, scope, scaffoldState) },
        drawerGesturesEnabled = true,
        drawerBackgroundColor = blueBackground
    ) {
        BottomNavHost(navHostController = navController)
        }
}