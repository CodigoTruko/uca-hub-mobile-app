package com.codigotruko.ucahub.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.ui.theme.mainBackground
import com.codigotruko.ucahub.ui.view.fragments.ButtonNormalFragment
import com.codigotruko.ucahub.ui.view.fragments.HeaderFragment
import com.codigotruko.ucahub.ui.view.fragments.ImageUCAHUB


//navController: NavHostController
@Composable
fun profileView(navController: NavHostController){
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = mainBackground)
    ){
        item{

            HeaderFragment()

            ImageUCAHUB()

            ButtonNormalFragment(navController = navController, textValue = "Editar", icon = "edit")

            Text(text = "My USer")

            Text(text = "Carnet:")
            Text(text = "O00XXXXX")

        }
    }
}