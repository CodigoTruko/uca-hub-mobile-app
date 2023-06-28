package com.codigotruko.ucahub.ui.view.overlapelements

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties


@Composable
fun LogOutBox(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {

    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Cancelar")
                }
            },
            title = { Text(text = "Cerrar Sesión") },
            text = { Text(text = "¿Estas seguro que quieres cerrar sesión") }
        )
    }

}