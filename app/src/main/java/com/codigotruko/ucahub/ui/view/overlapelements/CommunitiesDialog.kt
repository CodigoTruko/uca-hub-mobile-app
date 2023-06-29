package com.codigotruko.ucahub.ui.view.overlapelements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import com.codigotruko.ucahub.ui.view.fragments.CommunitieMember


@Composable
fun AddCommunitieBox(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {

    val nameInputValue = remember { mutableStateOf(TextFieldValue()) }
    val descriptionInputValue = remember { mutableStateOf(TextFieldValue()) }

    if (show) {

        Dialog(onDismissRequest = { onDismiss() }) {
                Card(
                    colors = CardDefaults.cardColors(darkWhiteBackground),
                    modifier = Modifier
                        .height(600.dp)
                        .width(450.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {

                        Spacer(modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp))

                        TextField(
                            value = nameInputValue.value,
                            onValueChange = { nameInputValue.value = it },
                            placeholder = { Text(text = "Nombre de la comunidad") },
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(83.dp)
                                .padding(16.dp))

                        TextField(
                            value = descriptionInputValue.value,
                            onValueChange = { descriptionInputValue.value = it },
                            placeholder = { Text(text = "Descripción para mi comunidad") },
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .padding(16.dp))

                        Text(
                            text = "Administrar miembros",
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        )

                        Button(
                            onClick = {  },
                            colors = ButtonDefaults.buttonColors(containerColor = blueBackground),
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        ) {
                            Icon(painter = painterResource(id = R.drawable.add_icon), contentDescription = "Icono agregar")
                            Text(text = "Agregar Miembro", color = Color.White)
                        }

                        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)) {
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(4.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.imagen_perfil_prueba),
                                        contentDescription = "Imagen de perfil",
                                        modifier = Modifier.width(25.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    // TODO : Adaptar una variable para el nombre de usuario.
                                    Text(
                                        text = "Nombre de usuario",
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 17.sp
                                    )
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.delete_icon),
                                            contentDescription = "Boton eliminar",
                                            tint = Color.Unspecified,
                                            modifier = Modifier.width(20.dp))
                                    }
                                }
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
                                onClick = { /* TODO : Implementar que guarde la nueva comunidad */  },
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


@Composable
fun CommunitieInformationBox(show: Boolean, onDismiss: () -> Unit) {

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Card(
                colors = CardDefaults.cardColors(darkWhiteBackground),
                modifier = Modifier
                    .height(600.dp)
                    .width(450.dp)
            ) {
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Nombre de la comunidad",
                    textAlign = TextAlign.Center,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Text(
                    text = "Descripción de la comunidad aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                    textAlign = TextAlign.Justify,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                )

                Text(
                    text = "Miembros",
                    textAlign = TextAlign.Center,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.padding(8.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        CommunitieMember()
                        CommunitieMember()
                        CommunitieMember()
                        CommunitieMember()
                        CommunitieMember()
                        CommunitieMember()
                        CommunitieMember()
                        CommunitieMember()
                        CommunitieMember()
                        CommunitieMember()

                    }
                }
            }
        }
    }
}