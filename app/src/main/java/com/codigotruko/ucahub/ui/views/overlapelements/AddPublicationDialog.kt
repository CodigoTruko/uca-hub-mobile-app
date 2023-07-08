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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.data.db.models.Publication
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun AddEditPublicationBox (show: Boolean, onDismiss: () -> Unit, addPublication: Boolean, publication: Publication?) {

    val app = LocalContext.current.applicationContext as UcaHubApplication
    val scope = CoroutineScope(Dispatchers.Main)

    // Variables de los EditText.
    val publicationTitleInput = remember { mutableStateOf(TextFieldValue()) }
    val publicationDescInput = remember { mutableStateOf(TextFieldValue()) }

    val editPublicationTitleInput = remember { mutableStateOf(publication?.let { TextFieldValue(it.title) }) }
    val editPublicationDescInput = remember { mutableStateOf(publication?.let { TextFieldValue(it.description) }) }

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
                        // TODO : Adaptar una variable para el nombre de usuario.
                        androidx.compose.material.Text(
                            text = "Nombre de usuario",
                            fontWeight = FontWeight.Medium,
                            fontSize = 17.sp
                        )
                    }
                    if (addPublication) {
                        TextField(
                            value = publicationTitleInput.value,
                            onValueChange = { publicationTitleInput.value = it },
                            placeholder = { Text(text = "Titulo para mi publicación") },
                            singleLine = true,
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .padding(16.dp))

                        TextField(
                            value = publicationDescInput.value,
                            onValueChange = { publicationDescInput.value = it },
                            placeholder = { Text(text = "Descripción para mi publicación") },
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .padding(16.dp))
                    } else {
                        editPublicationTitleInput.value?.let {
                            TextField(
                                value = it,
                                onValueChange = { editPublicationTitleInput.value = it },
                                placeholder = { Text(text = "Titulo para mi publicación") },
                                singleLine = true,
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(90.dp)
                                    .padding(16.dp))
                        }

                        editPublicationDescInput.value?.let {
                            TextField(
                                value = it,
                                onValueChange = { editPublicationDescInput.value = it },
                                placeholder = { Text(text = "Descripción para mi publicación") },
                                shape = RoundedCornerShape(4.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(160.dp)
                                    .padding(16.dp))
                        }
                    }
                    Row() {
                        Button(
                            onClick = { onDismiss() },
                            colors = ButtonDefaults.buttonColors(containerColor = blueBackground),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        ) { Text(text = "Cancelar", color = Color.White) }
                        Spacer(modifier = Modifier.width(25.dp))
                        Button(
                            onClick = {
                                scope.launch {
                                    app.createPublication(publicationTitleInput.value.text, publicationDescInput.value.text)
                                }
                                onDismiss()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = blueBackground),
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