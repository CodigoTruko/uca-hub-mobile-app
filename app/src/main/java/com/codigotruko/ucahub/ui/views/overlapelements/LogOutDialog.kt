package com.codigotruko.ucahub.ui.views.overlapelements

import android.content.Intent
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.core.app.ComponentActivity
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.MainActivity
import com.codigotruko.ucahub.ui.SessionManager

@Composable
fun LogOutBox(show: Boolean, navController: NavHostController, onDismiss: () -> Unit, sessionManager: SessionManager) {

    val restartApp = remember { mutableStateOf(false) }

    RestartApp(restartApp)

    if (show) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = {
                    onDismiss()
                    sessionManager.clearCredentials()
                    restartApp.value = true
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

@Composable
fun RestartApp(restartApp: MutableState<Boolean>) {
    val context = LocalContext.current as ComponentActivity

    // Reinicia la aplicación al cambiar el estado
    if (restartApp.value) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        context.finish()
    }
}