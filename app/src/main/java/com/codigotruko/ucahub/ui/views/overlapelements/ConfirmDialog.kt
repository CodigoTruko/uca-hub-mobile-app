package com.codigotruko.ucahub.ui.views.overlapelements

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ConfirmBox(show: Boolean, onDismiss: () -> Unit, action: () -> Unit = {}) {

    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = {
                    onDismiss()
                    action()

                }) {
                    Text(text = "Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Cancelar")
                }
            },
            title = { Text(text = "Confirmar Acción", fontWeight = FontWeight.SemiBold) },
            text = { Text(text = "¿Estas seguro que quieres borrar esta publicación?") }
        )
    }

}