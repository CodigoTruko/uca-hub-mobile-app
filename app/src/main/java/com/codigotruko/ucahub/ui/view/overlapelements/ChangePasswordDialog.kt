package com.codigotruko.ucahub.ui.view.overlapelements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
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
fun ChangePasswordBox(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {

    val passwordInput = remember { mutableStateOf(TextFieldValue()) }
    val newPasswordInput = remember { mutableStateOf(TextFieldValue()) }
    val ConfirmNewPassInput = remember { mutableStateOf(TextFieldValue()) }

    if (show) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Card(colors = CardDefaults.cardColors(darkWhiteBackground)) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "Cambiar contraseña",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(16.dp))

                    TextField(
                        value = passwordInput.value,
                        onValueChange = { passwordInput.value = it },
                        placeholder = { Text(text = "Contraseña actual") },
                        singleLine = true,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp))

                    TextField(
                        value = newPasswordInput.value,
                        onValueChange = { newPasswordInput.value = it },
                        placeholder = { Text(text = "Contraseña nueva") },
                        singleLine = true,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp))

                    TextField(
                        value = ConfirmNewPassInput.value,
                        onValueChange = { ConfirmNewPassInput.value = it },
                        placeholder = { Text(text = "Confirmar contraseña nueva") },
                        singleLine = true,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
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
                        ) { androidx.compose.material.Text(text = "Guardar", color = Color.White) }
                    }

                }
            }
        }
    }
}