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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.presentation.profile.ProfileViewModel
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.presentation.profile.ProfileViewModelFactory
import com.codigotruko.ucahub.ui.theme.mainBackground
import com.codigotruko.ucahub.ui.views.fragments.ButtonNormalFragment
import com.codigotruko.ucahub.ui.views.fragments.ImageUCAHUB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation")
@Composable
fun ProfileUserView(userIdentifier: String){


    val app = LocalContext.current.applicationContext as UcaHubApplication

    val profileViewModelFactory = ProfileViewModelFactory(app.profileRepository, app.getToken(), userIdentifier)
    val profile: ProfileViewModel = viewModel(factory = profileViewModelFactory)
    val profileResponse by profile.profileResponse.collectAsState()

    val dataProfile = profileResponse?.profile

    var faculty = "Facultad no asignada"
    var carrer = "Carrera no asignada"

    if(dataProfile?.program?.isNotEmpty() == true){
        faculty = dataProfile.program[0].faculty[0].name
        carrer = dataProfile.program[0].name
    }


    val usuario: String = dataProfile?.username ?: "xd"
    val nombre: String = dataProfile?.name ?: ""
    val descripcion: String = dataProfile?.description ?: ""

    val carnet: String = dataProfile?.carnet ?: ""

    val scope = CoroutineScope(Dispatchers.Main)

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = mainBackground)
            ){
                item{

                    ImageUCAHUB()

                    ButtonNormalFragment(true, textValue = "follow") {
                        scope.launch {
                            if (usuario != null) {
                                app.changeStateFollow(usuario)
                            }
                        }
                    }

                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        if (usuario != null) {
                            Text(
                                text = usuario,
                                fontSize = 30.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }

                        TextProfileFragment(name = "Nombre", value = nombre)

                        TextProfileFragment(name = "Carnet", value = carnet)

                        TextProfileFragment(name = "Facultad", value = faculty)

                        TextProfileFragment(name = "Carrera", value = carrer)

                        TextProfileFragment(name = "Descripción", value = descripcion)

                    }

                    Button(
                        onClick = {/* TODO : Hacer on click para buscar en perfil. */},
                        colors = ButtonDefaults.buttonColors(blueBackground),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.search_icon),
                            tint = Color.White,
                            contentDescription = "Icono buscar."
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Buscar en el perfil")
                    }

                    Spacer(modifier = Modifier.fillMaxWidth().height(60.dp))
                }
            }

}
