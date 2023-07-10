package com.codigotruko.ucahub.ui.views.menudesplegable

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.ui.SessionManager
import com.codigotruko.ucahub.ui.views.overlapelements.ChangePasswordBox
import com.codigotruko.ucahub.ui.views.overlapelements.FollowingBox
import com.codigotruko.ucahub.ui.views.overlapelements.LogOutBox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


// Vista del Menu desplegable.
@SuppressLint("SuspiciousIndentation")
@Composable
fun Menu(menu_items: List<Destinos>, navController: NavHostController, scope: CoroutineScope, scaffoldState: ScaffoldState, sessionManager: SessionManager) {

    val currentRoute = currentRoute(navController)
    var showFollowingBox by rememberSaveable() { mutableStateOf(false) }
    var showChangePassBox by rememberSaveable() { mutableStateOf(false) }
    var showLogOutBox by rememberSaveable() { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(20.dp)) {
        menu_items.forEach { item ->
            MenuItem(item = item, selected = currentRoute == item.route) {
                // Si da click al boton de personas que sigue
                if (item.route == "followingView") {
                    showFollowingBox = true
                }
                // Si da click al boton bookmarks
                if (item.route == "bookmarksView") {

                }
                // Si da click al boton settings
                if (item.route == "settingsview") {
                    showChangePassBox = true
                }
                // Si da click al boton log_out
                if (item.route == "log_outView") {
                    showLogOutBox = true
                }
                scope.launch { scaffoldState.drawerState.close() }
            }
        }
    }

    // Muestra los dialogos.
    FollowingBox(showFollowingBox, { showFollowingBox = false }, {  })
    ChangePasswordBox(showChangePassBox, { showChangePassBox = false }, {})
    LogOutBox(showLogOutBox, navController, { showLogOutBox = false }, sessionManager = sessionManager)

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