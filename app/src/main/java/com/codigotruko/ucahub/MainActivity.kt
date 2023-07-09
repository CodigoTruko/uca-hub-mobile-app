package com.codigotruko.ucahub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codigotruko.ucahub.ui.views.LogInView
import com.codigotruko.ucahub.ui.views.bottomnavbar.StaticItems
import com.codigotruko.ucahub.ui.views.RegisterView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LogInView(navController)
                }
                composable("register"){
                    RegisterView(navController)
                }
                composable("mainfeed") {
                    StaticItems()
                }
            }
        }
    }
}