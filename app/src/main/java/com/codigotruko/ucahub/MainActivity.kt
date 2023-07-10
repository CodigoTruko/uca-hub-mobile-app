package com.codigotruko.ucahub

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codigotruko.ucahub.ui.theme.Purple40
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.mainBackground
import com.codigotruko.ucahub.ui.views.LogInView
import com.codigotruko.ucahub.ui.views.bottomnavbar.StaticItems
import com.codigotruko.ucahub.ui.views.RegisterView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = 0xFFC5CAE9.toInt()
        setContent {

            val navController = rememberNavController()

            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

            SetStatusBarColor(currentRoute)

            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LogInView(navController)
                }
                composable("register") {
                    RegisterView(navController)
                }
                composable("mainfeed") {
                    StaticItems()
                }
            }
        }
    }

    @Composable
    fun SetStatusBarColor(currentRoute: String?) {
        Log.d("StatusBarColor", "Current route: $currentRoute")
        val statusBarColor = getColorForRoute(currentRoute)

        LaunchedEffect(statusBarColor) {
            window.statusBarColor = statusBarColor.toArgb()
        }
    }

    @Composable
    fun getColorForRoute(route: String?): Color {
        return when (route) {
            "login", "register" -> mainBackground
            else -> blueBackground
        }
    }
}
