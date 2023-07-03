package com.codigotruko.ucahub.ui.views.bottombarviews

import com.codigotruko.ucahub.data.db.models.Author


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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.presentation.publication.PublicationListViewModel
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.mainBackground
import com.codigotruko.ucahub.ui.views.fragments.ButtonNormalFragment
import com.codigotruko.ucahub.ui.views.fragments.ImageUCAHUB
import com.codigotruko.ucahub.ui.views.overlapelements.EditProfileBox
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun ProfileUserView(show: Boolean, onDismiss: ()->Unit, author: Author){


    val app = LocalContext.current.applicationContext as UcaHubApplication
    val scope = CoroutineScope(Dispatchers.Main)

    if (show){
        Dialog(onDismissRequest = { onDismiss()}) {
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
                            app.changeStateFollow(author.username)
                        }
                    })

                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = author.username,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        TextProfileFragment(name = "Carnet", value = "000XXXXXX")

                        TextProfileFragment(name = "Facultad", value = "Ingenieria y Arquitectura")

                        TextProfileFragment(name = "Carrera", value = "Ingenieria Informatica")

                        TextProfileFragment(name = "Descripción", value = "Descripcion")


                    }


                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp))

                }
            }

        }
    }


}
