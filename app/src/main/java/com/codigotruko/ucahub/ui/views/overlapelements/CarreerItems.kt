package com.codigotruko.ucahub.ui.views.overlapelements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun FacultadMenu(
    optionSelectedFaculty: MutableState<Boolean>,
    optionSelectedFacultyValue: MutableState<String>
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    var id by remember { mutableStateOf(Int) }

    optionSelectedFacultyValue.value = selectedOption

    Card(
        colors = CardDefaults.cardColors(Color.LightGray),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {

        if (selectedOption == "")
            Text(text = "Selecciona tu facultad", modifier = Modifier
                .padding(16.dp)
                .clickable { expanded = true })
        else {
            Text(text = selectedOption, modifier = Modifier
                .padding(16.dp)
                .clickable { expanded = true })
            optionSelectedFaculty.value = true
        }

        Column {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(IntrinsicSize.Min)
            ) {
                DropdownMenuItem(onClick = {
                    selectedOption = "Ingenieria y Arquitectura"
                    expanded = false
                }) {
                    Text(text = "Ingenieria y Arquitectura")
                }
                DropdownMenuItem(onClick = {
                    selectedOption = "Ciencias Sociales y Humanidades"
                    expanded = false
                }) {
                    Text(text = "Ciencias Sociales y Humanidades")
                }
            }
        }
    }
}

@Composable
fun CarrerMenu(optionSelected: MutableState<Boolean>, optionSelectedFaculty: MutableState<String>): String {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }


    if (optionSelected.value == false) {
        Card(
            colors = CardDefaults.cardColors(Color.LightGray),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
                Text(text = "Selecciona primero una facultad", modifier = Modifier
                    .padding(16.dp))
        }
    }

    if (optionSelected.value == true && optionSelectedFaculty.value == "Ingenieria y Arquitectura") {
        Card(
            colors = CardDefaults.cardColors(Color.LightGray),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            if (selectedOption == "")
                Text(text = "Selecciona tu carrera", modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        expanded = true
                    })
            else
                Text(text = selectedOption, modifier = Modifier
                    .padding(16.dp)
                    .clickable { expanded = true })

            Column {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.width(IntrinsicSize.Min)
                ) {
                    DropdownMenuItem(onClick = {
                        selectedOption = "Arquitectura"
                        id = "64ab96ed7be84927c4e5a6b8"
                        expanded = false
                    }) {
                        Text(text = "Arquitectura")
                    }
                    DropdownMenuItem(onClick = {
                        selectedOption = "Ingeniería de Alimentos"
                        id = "64a6d1ee44d193179a8481ff"
                        expanded = false
                    }) {
                        Text(text = "Ingeniería de Alimentos")
                    }
                    DropdownMenuItem(onClick = {
                        selectedOption = "Ingeniería Civil"
                        id ="64a6d22844d193179a848204"
                        expanded = false
                    }) {
                        Text(text = "Ingeniería Civil")
                    }
                    DropdownMenuItem(onClick = {
                        selectedOption = "Ingeniería Eléctrica"
                        id = "64a6d22b44d193179a848207"
                        expanded = false
                    }) {
                        Text(text = "Ingeniería Eléctrica")
                    }
                    DropdownMenuItem(onClick = {
                        selectedOption = "Ingeniería Energética"
                        id = "64a6d23e44d193179a84820c"
                        expanded = false
                    }) {
                        Text(text = "Ingeniería Energética")
                    }
                    DropdownMenuItem(onClick = {
                        selectedOption = "Ingeniería Industrial"
                        id = "64a6d24a44d193179a84820f"
                        expanded = false
                    }) {
                        Text(text = "Ingeniería Industrial")
                    }
                    DropdownMenuItem(onClick = {
                        selectedOption = "Ingeniería Informática"
                        id = "64a6d25c44d193179a848212"
                        expanded = false
                    }) {
                        Text(text = "Ingeniería Informática")
                    }
                    DropdownMenuItem(onClick = {
                        selectedOption = "Ingeniería Mecánica"
                        id = "64a6d26344d193179a848215"
                        expanded = false
                    }) {
                        Text(text = "Ingeniería Mecánica")
                    }
                    DropdownMenuItem(onClick = {
                        selectedOption = "Ingeniería Química"
                        id = "64a6d26b44d193179a848218"
                        expanded = false
                    }) {
                        Text(text = "Ingeniería Química")
                    }
                }
            }
        }
    }

    if (optionSelected.value == true && optionSelectedFaculty.value == "Ciencias Sociales y Humanidades") {
        Card(
            colors = CardDefaults.cardColors(Color.LightGray),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            if (selectedOption == "")
                Text(text = "Selecciona tu carrera", modifier = Modifier
                    .padding(16.dp)
                    .clickable { expanded = true })
            else
                Text(text = selectedOption, modifier = Modifier
                    .padding(16.dp)
                    .clickable { expanded = true })

            Column {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.width(IntrinsicSize.Min)
                ) {
                    DropdownMenuItem(onClick = {
                        selectedOption = "Licenciatura en Ciencias Jurídicas"
                        id = "64a6d29544d193179a84821e"
                        expanded = false
                    }) {
                        Text(text = "Licenciatura en Ciencias Jurídicas")
                    }
                    DropdownMenuItem(onClick = {
                        selectedOption = "Licenciatura en Psicología"
                        id = "64a6d28c44d193179a84821b"
                        expanded = false
                    }) {
                        Text(text = "Licenciatura en Psicología")
                    }
                    DropdownMenuItem(onClick = {
                        selectedOption = "Licenciatura en Idioma Inglés"
                        id = "64a6d2b244d193179a848224"
                        expanded = false
                    }) {
                        Text(text = "Licenciatura en Idioma Inglés")
                    }
                    DropdownMenuItem(onClick = {
                        selectedOption = "Licenciatura en Teología"
                        id = "64a6d2a844d193179a848221"
                        expanded = false
                    }) {
                        Text(text = "Licenciatura en Teología")
                    }

                }
            }
        }
    }

    return id
}