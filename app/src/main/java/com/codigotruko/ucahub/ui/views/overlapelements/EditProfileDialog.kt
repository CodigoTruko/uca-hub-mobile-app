package com.codigotruko.ucahub.ui.views.overlapelements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground

@Composable
fun EditProfileBox (show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    val nameProfile = remember { mutableStateOf(TextFieldValue()) }
    val carnetProfile = remember { mutableStateOf(TextFieldValue()) }
    val facultadProfile = remember { mutableStateOf(TextFieldValue()) }
    val careerProfile = remember { mutableStateOf(TextFieldValue()) }
    val descriptionProfile = remember { mutableStateOf(TextFieldValue()) }

    val optionSelectedFaculty = remember { mutableStateOf(false) }
    val optionSelectedFacultyValue = remember { mutableStateOf("") }

    if (show) {
        Dialog(onDismissRequest = { onDismiss()
                                    optionSelectedFaculty.value = false
        }
        ) {
            Card(colors = CardDefaults.cardColors(darkWhiteBackground)) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "Editar información de tu perfil",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(16.dp))

                    TextField(
                        value = nameProfile.value,
                        onValueChange = { nameProfile.value = it },
                        placeholder = { Text(text = "Nombre") },
                        singleLine = true,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp))

                    TextField(
                        value = carnetProfile.value,
                        onValueChange = { carnetProfile.value = it },
                        placeholder = { Text(text = "Carnet") },
                        singleLine = true,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp))

                    FacultadMenu(optionSelectedFaculty, optionSelectedFacultyValue)

                    CarrerMenu(optionSelectedFaculty, optionSelectedFacultyValue)

                    TextField(
                        value = descriptionProfile.value,
                        onValueChange = { descriptionProfile.value = it },
                        placeholder = { Text(text = "Descripción") },
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp))

                    Row() {
                        Button(
                            onClick = {
                                onDismiss()
                                optionSelectedFaculty.value = false
                                      },
                            colors = ButtonDefaults.buttonColors(containerColor = blueBackground),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        ) { Text(text = "Cancelar", color = Color.White) }
                        Spacer(modifier = Modifier.width(25.dp))
                        Button(
                            onClick = { /* TODO : Implementar que guarde la nueva informacion del perfil */  },
                            colors = ButtonDefaults.buttonColors(containerColor = blueBackground),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        ) { Text(text = "Guardar", color = Color.White) }
                    }

                }
            }
        }
    }
}