package com.codigotruko.ucahub.ui.view.MenuDesplegable

import com.codigotruko.ucahub.R

sealed class Destinos (
    val icon : Int,
    val title : String,
    val ruta : String
    ) {
    object Pantalla1 : Destinos(R.drawable.home_icon, "Following", "followingView")
    object Pantalla2 : Destinos(R.drawable.home_icon, "Bookmarks", "bookmarksView")
    object Pantalla3 : Destinos(R.drawable.home_icon, "Settings", "settingsview")
    object Pantalla4 : Destinos(R.drawable.home_icon, "Log Out", "log_outView")
}
