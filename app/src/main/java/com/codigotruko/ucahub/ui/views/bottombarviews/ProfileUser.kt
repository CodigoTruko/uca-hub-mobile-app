package com.codigotruko.ucahub.ui.views.bottombarviews

import android.annotation.SuppressLint


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.presentation.profile.ProfileViewModel
import com.codigotruko.ucahub.ui.theme.mainBackground
import com.codigotruko.ucahub.ui.views.fragments.ButtonNormalFragment
import com.codigotruko.ucahub.ui.views.fragments.ImageUCAHUB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@Composable
fun ProfileUserView(userIdentifier: String){

    //val profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory(userIdentifier))
    //val profile by profileViewModel.profileResponse.collectAsState()

    val usuario: String = "XD"
    val nombre: String = "XD"
    val descripcion: String = "XD"

    val app = LocalContext.current.applicationContext as UcaHubApplication
    val scope = CoroutineScope(Dispatchers.Main)

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = mainBackground)
            ){
                item{

                    ImageUCAHUB()

                    ButtonNormalFragment(textValue = "follow", onclick = {
                        scope.launch {
                            app.changeStateFollow(usuario)
                        }
                    })

                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = usuario,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        TextProfileFragment(name = "Nombre", value = nombre)

                        TextProfileFragment(name = "Carnet", value = "XXXXXXXX")

                        TextProfileFragment(name = "Facultad", value = "Ingenieria y Arquitectura")

                        TextProfileFragment(name = "Carrera", value = "Ingenieria Informatica")

                        TextProfileFragment(name = "Descripción", value = userIdentifier)

                    }

                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp))

                }
            }

}
