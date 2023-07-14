package com.codigotruko.ucahub.ui.views.overlapelements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.data.db.models.Profile
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditProfileBox (show: Boolean, onDismiss: () -> Unit, profile: Profile?, onConfirm: () -> Unit = {}) {

    val valuesProgram = profile?.program

    var faculty = "Facultad no asignada"
    var carrer = "Carrera no asignada"

    if (valuesProgram != null) {
        if (valuesProgram.isNotEmpty()){
            faculty = valuesProgram[0].faculty[0].name
            carrer = valuesProgram[0].name
        }
    }

    val nameProfile = remember { mutableStateOf(profile?.let { TextFieldValue(it.name) }) }
    val carnetProfile = remember { mutableStateOf(profile?.let { TextFieldValue(it.carnet) }) }
    val usernameProfile = remember { mutableStateOf(profile?.let { TextFieldValue(it.username) }) }
    val emailProfile = remember { mutableStateOf(profile?.let { TextFieldValue(it.email) }) }

    val facultadProfile = remember { mutableStateOf(TextFieldValue(faculty)) }
    val careerProfile = remember { mutableStateOf(TextFieldValue(carrer)) }
    val descriptionProfile = remember { mutableStateOf(profile?.description?.let { TextFieldValue(it) }) }

    val context = LocalContext.current

    val app = context.applicationContext as UcaHubApplication
    val scope = CoroutineScope(Dispatchers.Main)
    val expanded = remember { mutableStateOf(false) }
    val items = remember { mutableStateListOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5") }
    val selectedItem = remember { mutableStateOf(items[0]) }

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

                    nameProfile.value?.let { it ->
                        TextField(
                            value = it,
                            onValueChange = { nameProfile.value = it },
                            placeholder = { Text(text = "Nombre") },
                            singleLine = true,
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp))
                    }

                    carnetProfile.value?.let {it ->
                        TextField(
                            value = it,
                            onValueChange = { carnetProfile.value = it },
                            placeholder = { Text(text = "Carnet") },
                            singleLine = true,
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp))
                    }

                    /*
                    Column(modifier = Modifier.fillMaxWidth()) {
                        TextField(
                            value = selectedItem.value,
                            onValueChange = { selectedItem.value = it},
                            placeholder = { Text(text = "Carnet") },
                            singleLine = true,
                            readOnly = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable {
                                    expanded.value = true
                                },

                            )

                        if(expanded.value){
                            DropdownMenu(
                                expanded = expanded.value,
                                onDismissRequest = { expanded.value = false }
                            ) {
                                items.forEach { item ->
                                    DropdownMenuItem(onClick = {
                                        selectedItem.value = item
                                        expanded.value = false
                                    }) {
                                        Text(text = item)
                                    }
                                }
                            }
                        }

                    }
                    */

                    FacultadMenu(optionSelectedFaculty, optionSelectedFacultyValue)

                    CarrerMenu(optionSelectedFaculty, optionSelectedFacultyValue)

                    descriptionProfile.value?.let { it ->
                        TextField(
                            value = it,
                            onValueChange = { descriptionProfile.value = it },
                            placeholder = { Text(text = "Descripción") },
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp))
                    }

                    Row{
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
                            onClick = {
                                scope.launch {
                                    nameProfile.value?.let {
                                        carnetProfile.value?.let { it1 ->
                                            emailProfile.value?.let { it2 ->
                                                usernameProfile.value?.let { it3 ->
                                                    descriptionProfile.value?.let { it4 ->
                                                        app.changeProfileInfo(
                                                            it.text,
                                                            it1.text,
                                                            it3.text,
                                                            it2.text,
                                                            "64a6d25c44d193179a848212",
                                                            it4.text,
                                                            "1"
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    onConfirm()
                                }
                                onDismiss()
                                      },
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

@Composable
fun ComboBoxExample() {



}
