package com.codigotruko.ucahub.ui.view.menudesplegable

import android.annotation.SuppressLint
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
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope


// Vista del Menu desplegable.
@SuppressLint("SuspiciousIndentation")
@Composable
fun Menu(menu_items: List<Destinos>, navController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState) {

    val currentRoute = currentRoute(navController)
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(20.dp)) {
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
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            tint = if (selected) Color.Gray else Color.White,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = item.title,
            style = TextStyle(fontSize = 18.sp),
            color = if (selected) Color.Gray else Color.White
        )
    }
}