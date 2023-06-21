package com.codigotruko.ucahub.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.ui.theme.mainBackground
import com.codigotruko.ucahub.ui.view.fragments.ButtonNormalFragment
import com.codigotruko.ucahub.ui.view.fragments.HeaderFragment
import com.codigotruko.ucahub.ui.view.fragments.ImageUCAHUB
import com.codigotruko.ucahub.ui.view.fragments.PublicationFragment


//navController: NavHostController


@Composable
fun ProfileView(navController: NavHostController, userName: String?, carnet: String?, faculty: String?, carrer: String?, description: String?, userID: String){

    val myID = "1" //Check if is my user or other

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = mainBackground)
    ){
        item{

            HeaderFragment()

            ImageUCAHUB()


            ButtonNormalFragment(
                navController = navController,
                textValue = if(userID == myID) "Editar" else "Seguir",
                icon = if(userID == myID) "edit" else "Follow",
                padding = Dp(0f)

            )

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = userName.toString(),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                TextProfileFragment(name = "Carnet", value = carnet)

                TextProfileFragment(name = "Facultad", value = faculty)

                TextProfileFragment(name = "Carrera", value = carrer)

                TextProfileFragment(name = "Descripción", value = description)


            }

            PublicationFragment(userName = userName.toString(), description = "Descripción")

            PublicationFragment(userName = userName.toString(), description = "Descripción")


        }
    }
}

@Composable
fun TextProfileFragment(name: String?, value: String?){
    Column{
        Text(
            text = name.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value.toString(),
            fontSize = 20.sp
        )
    }
}