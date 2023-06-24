package com.codigotruko.ucahub.ui.view.overlayelements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground



@Composable
fun CommunitiesBox(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {

    val inputValue = remember { mutableStateOf(TextFieldValue()) }

    if (show) {

        Dialog(onDismissRequest = { onDismiss() }) {
            Box(
                modifier = Modifier
                    .height(600.dp)
                    .width(450.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(darkWhiteBackground),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Nombre de la comunidad",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )

                    TextField(
                        value = inputValue.value,
                        onValueChange = { inputValue.value = it },
                        placeholder = { Text(text = "Descripción para mi comunidad") },
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(16.dp))

                    Text(
                        text = "Administrar miembros",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )

                }
            }
        }
    }
}