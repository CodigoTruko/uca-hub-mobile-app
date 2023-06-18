package com.codigotruko.ucahub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codigotruko.ucahub.ui.view.bottomnavbar.StaticItems
import com.codigotruko.ucahub.ui.view.logInView
import com.codigotruko.ucahub.ui.view.profileView
import com.codigotruko.ucahub.ui.view.registerView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    logInView(navController)
                }
                composable("mainfeed") {
                    StaticItems()
                }
                composable("register"){
                    registerView(navController)
                }
                composable("profile"){
                    profileView(navController)
                }
            }
        }
    }
}