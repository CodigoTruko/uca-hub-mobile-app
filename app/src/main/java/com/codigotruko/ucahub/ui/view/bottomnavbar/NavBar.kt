package com.codigotruko.ucahub.ui.view.bottomnavbar

import android.annotation.SuppressLint
import android.widget.Space
import androidx.annotation.RestrictTo
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.lightBlueBackground
import com.codigotruko.ucahub.ui.view.MenuDesplegable.Destinos
import com.codigotruko.ucahub.ui.view.MenuDesplegable.Destinos.*
import com.codigotruko.ucahub.ui.view.MenuDesplegable.currentRoute

val listItems = listOf(
    NavBarElements.Home,
    NavBarElements.Communities,
    NavBarElements.Profile,
    NavBarElements.Menu
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun StaticItems(){

    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val navigationItems = listOf(
        Pantalla1,
        Pantalla2,
        Pantalla3,
        Pantalla4
    )

    androidx.compose.material.Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar() },
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
        drawerContent = { Menu(menu_items = navigationItems, navController) },
        drawerGesturesEnabled = true,
        drawerBackgroundColor = blueBackground
    ) {
        BottomNavHost(navHostController = navController, scope, scaffoldState)
        }
}


@Composable
fun Menu(menu_items : List<Destinos>, navController : NavHostController) {
    val currentRoute = currentRoute(navController)
    Column(modifier = Modifier.padding(20.dp)) {
        menu_items.forEach { item ->
            MenuItem(item = item, selected = currentRoute == item.ruta) {

            }
        }
    }

}

@Composable
fun MenuItem (item : Destinos, selected : Boolean, onItemClick : (Destinos) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(12))
            .background(if (selected) Color.Black.copy(alpha = 0.25f) else Color.Transparent)
            .padding(8.dp)
            .clickable { onItemClick(item) }
    ) {
        Icon(painter = painterResource(id = R.drawable.home_icon),
            contentDescription = item.title,
            tint = if (selected) Color.White else Color.Black,
            modifier = Modifier.size(32.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = item.title,
            style = TextStyle(fontSize = 18.sp),
            color = if(selected) Color.White else Color.Black)
    }
}