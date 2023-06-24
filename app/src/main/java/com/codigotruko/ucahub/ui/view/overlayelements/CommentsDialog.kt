package com.codigotruko.ucahub.ui.view.overlayelements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.commentBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground


@Composable
fun CommentsBox(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {

    if (show) {
        val inputValue = remember { mutableStateOf(TextFieldValue()) }

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
                        text = "Comentarios",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(490.dp)
                    ) {
                        item {

                            Card(
                                colors = CardDefaults.cardColors(commentBackground),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)
                                    .padding(4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(0.6f)
                                        .padding(4.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.imagen_perfil_prueba),
                                        contentDescription = "Imagen de perfil",
                                        modifier = Modifier.width(25.dp)
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    // TODO : Adaptar una variable para el nombre de usuario.
                                    Text(
                                        text = "Nombre de usuario",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 17.sp
                                    )
                                }
                                // TODO : Adaptar una variable para el comentario del usuario.
                                Text(
                                    text = "Comentario del usuariooooooo",
                                    modifier = Modifier.padding(horizontal = 24.dp)
                                )
                            }

                        }
                    }

                    Row(modifier = Modifier.fillMaxWidth()) {
                        TextField(
                            value = inputValue.value, onValueChange = { inputValue.value = it },
                            placeholder = {
                                Text(
                                    text = "Comentar publicación",
                                    fontSize = 15.sp,
                                    modifier = Modifier.fillMaxSize()
                                )
                            },
                            leadingIcon = {
                                IconButton(onClick = { onConfirm() }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.send_icon),
                                        contentDescription = "Icono Send"
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        )
                    }

                }

            }
        }
    }
}