package com.codigotruko.ucahub.ui.views.overlapelements

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.ui.SessionManager

@Composable
fun LogOutBox(show: Boolean, navController: NavHostController, onDismiss: () -> Unit, sessionManager: SessionManager) {

    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = {
                    onDismiss()
                    sessionManager.clearCredentials()
                }) {
                    Text(text = "Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Cancelar")
                }
            },
            title = { Text(text = "Cerrar Sesión", fontWeight = FontWeight.SemiBold) },
            text = { Text(text = "¿Estas seguro que quieres cerrar sesión?") }
        )
    }

}