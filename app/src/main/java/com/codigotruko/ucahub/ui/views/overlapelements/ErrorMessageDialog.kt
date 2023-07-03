package com.codigotruko.ucahub.ui.views.overlapelements

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable


@Composable
fun ErrorMessageBox(show: Boolean, onConfirm: () -> Unit) {

    if (show) {
        AlertDialog(
            onDismissRequest = { onConfirm() },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Aceptar")
                }
            },
            title = { Text(text = "Error") },
            text = { Text(text = "Lo sentimos, no se ha podido realizar la acción esperada.") }
        )
    }

}