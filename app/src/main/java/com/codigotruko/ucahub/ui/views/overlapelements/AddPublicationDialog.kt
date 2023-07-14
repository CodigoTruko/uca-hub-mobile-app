package com.codigotruko.ucahub.ui.views.overlapelements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.data.db.models.Publication
import com.codigotruko.ucahub.presentation.profile.MyProfileViewModel
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import com.codigotruko.ucahub.ui.theme.disabledBlueBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun AddEditPublicationBox (
    show: Boolean,
    onDismiss: () -> Unit,
    addPublication: Boolean,
    publication: Publication?,
    _aux: Boolean,
    placeRoute: String,
    action: () -> Unit = {},
)
{

    val app = LocalContext.current.applicationContext as UcaHubApplication
    val scope = CoroutineScope(Dispatchers.Main)

    val profileViewModel: MyProfileViewModel = viewModel(factory = MyProfileViewModel.Factory)
    val profile by profileViewModel.myProfileResponse.collectAsState()

    val author = profile?.profile?.username

    // Variables de los EditText.
    val publicationTitleInput = remember { mutableStateOf("") }
    val publicationDescInput = remember { mutableStateOf("") }

    val editPublicationTitleInput = remember { mutableStateOf(publication?.let { TextFieldValue(it.title) }) }
    val editPublicationDescInput = remember { mutableStateOf(publication?.let { TextFieldValue(it.description) }) }

    // Para validar si el Button esta disponible.
    val isButtonEnabledTitle = remember { mutableStateOf(false) }
    val isButtonEnabledDesc = remember { mutableStateOf(false) }

    val isEnabledEditTittle = remember { mutableStateOf(true) }
    val isEnabledEditDesc = remember { mutableStateOf(true) }


    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Card(colors = CardDefaults.cardColors(darkWhiteBackground)) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (addPublication) {
                        Text(
                            text = "Crear nueva publicación",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        Text(
                            text = "Editar publicación",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(16.dp))
                    }

                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.imagen_perfil_prueba),
                            contentDescription = "Imagen de perfil",
                            modifier = Modifier.width(25.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        if (author != null) {
                            Text(
                                text = author,
                                fontWeight = FontWeight.Medium,
                                fontSize = 17.sp
                            )
                        }
                    }

                    if (addPublication) {
                        TextField(
                            value = publicationTitleInput.value,
                            onValueChange = { newValue ->
                                publicationTitleInput.value = newValue
                                isButtonEnabledTitle.value = newValue.isNotEmpty() },
                            placeholder = { Text(text = "Titulo para mi publicación") },
                            singleLine = true,
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(16.dp)).toString()

                        TextField(
                            value = publicationDescInput.value,
                            onValueChange = { newValue ->
                                publicationDescInput.value = newValue
                                isButtonEnabledDesc.value = newValue.isNotEmpty() },
                            placeholder = { Text(text = "Descripción para mi publicación") },
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .padding(16.dp)).toString()
                    } else {
                        editPublicationTitleInput.value?.let {
                            TextField(
                                value = it,
                                onValueChange = { newValue ->
                                    editPublicationTitleInput.value = newValue
                                    isEnabledEditTittle.value = newValue.text.isNotEmpty() },
                                placeholder = { Text(text = "Titulo para mi publicación") },
                                singleLine = true,
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(90.dp)
                                    .padding(16.dp)).toString()
                        }

                        editPublicationDescInput.value?.let {
                            TextField(
                                value = it,
                                onValueChange = { newValue ->
                                    editPublicationDescInput.value = newValue
                                    isEnabledEditDesc.value = newValue.text.isNotEmpty() },
                                placeholder = { Text(text = "Descripción para mi publicación") },
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(160.dp)
                                    .padding(16.dp)).toString()
                        }
                    }

                    Row {
                        Button(
                            onClick = { onDismiss() },
                            colors = ButtonDefaults.buttonColors(containerColor = blueBackground),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        ) { Text(text = "Cancelar", color = Color.White) }
                        Spacer(modifier = Modifier.width(25.dp))

                        if (addPublication) {
                            Button(
                                onClick = {
                                    scope.launch {
                                        if(placeRoute == "profile"){
                                            app.createPublication(
                                                publicationTitleInput.value,
                                                publicationDescInput.value
                                            )
                                        }
                                        if (placeRoute == "feed"){
                                            app.createFeedPublication(
                                                publicationTitleInput.value,
                                                publicationDescInput.value
                                            )
                                        }
                                        if (placeRoute == "community"){
                                            app.createFeedPublication(
                                                publicationTitleInput.value,
                                                publicationDescInput.value
                                            )
                                        }
                                    }
                                    action()
                                    onDismiss()
                                },
                                enabled = (isButtonEnabledTitle.value && isButtonEnabledDesc.value),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = blueBackground,
                                    disabledContainerColor = disabledBlueBackground,
                                    disabledContentColor = Color.White
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                            ) { Text(text = "Aceptar", color = Color.White) }
                        } else {
                            Button(
                                onClick = {
                                    scope.launch {
                                        publication?._id?.let {
                                            app.updatePublication(
                                                it,
                                                publicationTitleInput.value,
                                                publicationDescInput.value
                                            )
                                        }
                                    }
                                    action()
                                    onDismiss()
                                },
                                enabled = (isEnabledEditTittle.value && isEnabledEditDesc.value),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = blueBackground,
                                    disabledContainerColor = disabledBlueBackground,
                                    disabledContentColor = Color.White
                                ),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                            ) { Text(text = "Aceptar", color = Color.White) }
                        }
                    }

                }
            }
        }
    }

}