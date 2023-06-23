package com.codigotruko.ucahub.ui.view.MenuDesplegable

import com.codigotruko.ucahub.R

sealed class Destinos (
    val icon : Int,
    val title : String,
    val route : String
    ) {
    object Pantalla1 : Destinos(R.drawable.following_icon, "Following", "followingView")
    object Pantalla2 : Destinos(R.drawable.bookmark_icon, "Bookmarks", "bookmarksView")
    object Pantalla3 : Destinos(R.drawable.settings_icon, "Settings", "settingsview")
    object Pantalla4 : Destinos(R.drawable.logout_icon, "Log Out", "log_outView")
}
