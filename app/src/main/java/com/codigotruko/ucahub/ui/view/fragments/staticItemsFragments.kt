package com.codigotruko.ucahub.ui.view.fragments

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalDrawer
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.view.MenuDesplegable.Destinos
import com.codigotruko.ucahub.ui.view.MenuDesplegable.currentRoute
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
                    navController.navigate(screens.route) {

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
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


// Vista del Menu desplegable.
@SuppressLint("SuspiciousIndentation")
@Composable
fun Menu(menu_items: List<Destinos>, navController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState) {

    val currentRoute = currentRoute(navController)
    val drawerState = rememberDrawerState(DrawerValue.Closed)

        Column(modifier = Modifier.padding(20.dp)) {
            menu_items.forEach { item ->
                MenuItem(item = item, selected = currentRoute == item.route) {
//                    navController.navigate(item.route) {
//                        launchSingleTop = true
//                    }
//                    scope.launch {
//                        scaffoldState.drawerState.close()
//                    }
                }
            }
        }

}

@Composable
fun MenuItem(item: Destinos, selected: Boolean, onItemClick: (Destinos) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .background(if (selected) Color.Red.copy(alpha = 0.25f) else Color.Transparent)
            .padding(8.dp)
            .clickable { onItemClick(item) }) {
        Icon(
            painter = painterResource(id = R.drawable.home_icon),
            contentDescription = item.title,
            tint = if (selected) Color.Gray else Color.White,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        androidx.compose.material3.Text(
            text = item.title,
            style = TextStyle(fontSize = 18.sp),
            color = if (selected) Color.Gray else Color.White
        )
    }
}