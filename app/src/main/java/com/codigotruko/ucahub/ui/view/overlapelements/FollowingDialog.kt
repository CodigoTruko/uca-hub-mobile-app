package com.codigotruko.ucahub.ui.view.overlapelements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground


@Composable
fun FollowingBox(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {

    if (show) {

        Dialog(onDismissRequest = { onDismiss() }) {
            Card(
                colors = CardDefaults.cardColors(darkWhiteBackground),
                modifier = Modifier.height(600.dp).width(450.dp)
            ) {
                Text(
                    text = "Lista de personas que sigues",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.6f)
                                    .padding(horizontal = 16.dp)
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
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 17.sp
                                )
                                IconButton(onClick = { /*TODO*/ }) {
                                    androidx.compose.material3.Icon(
                                        painter = painterResource(id = R.drawable.delete_icon),
                                        contentDescription = "Boton eliminar",
                                        tint = Color.Unspecified,
                                        modifier = Modifier.width(20.dp)
                                    )
                                }
                            }
                        }

                    }
                }
            }
        }
}