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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground


@Composable
fun AddPublicationBox (show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {

    val publicationDescInput = remember { mutableStateOf(TextFieldValue()) }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Card(colors = CardDefaults.cardColors(darkWhiteBackground)) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "Crear nueva publicación",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(16.dp))

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

                    TextField(
                        value = publicationDescInput.value,
                        onValueChange = { publicationDescInput.value = it },
                        placeholder = { androidx.compose.material.Text(text = "Descripción para mi publicación") },
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .padding(16.dp))

                    Row() {
                        Button(
                            onClick = { onDismiss() },
                            colors = ButtonDefaults.buttonColors(containerColor = blueBackground),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        ) { androidx.compose.material.Text(text = "Cancelar", color = Color.White) }
                        Spacer(modifier = Modifier.width(25.dp))
                        Button(
                            onClick = { /* TODO : Implementar que guarde la nueva comunidad */  },
                            colors = ButtonDefaults.buttonColors(containerColor = blueBackground),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                        ) { androidx.compose.material.Text(text = "Aceptar", color = Color.White) }
                    }

                }
            }
        }
    }

}