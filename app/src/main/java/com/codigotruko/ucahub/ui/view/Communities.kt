package com.codigotruko.ucahub.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import com.codigotruko.ucahub.ui.theme.mainBackground
import com.codigotruko.ucahub.ui.view.fragments.iconFragment
import com.codigotruko.ucahub.ui.view.overlayelements.CommunitiesBox


@Preview (showBackground = true)
@Composable
fun CommunitiesView() {

    var showCommunitieBox by rememberSaveable() { mutableStateOf(false) }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(mainBackground)
    ) {

        item {

            Card(
                colors = CardDefaults.cardColors(darkWhiteBackground),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(6.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.imagen_comunidad),
                    contentDescription = "Imagen de comunidad.",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }

            Button(
                onClick = { showCommunitieBox = true },
                colors = ButtonDefaults.buttonColors(blueBackground),
                modifier = Modifier
                    .padding(vertical = 16.dp)
            ) { Text(text = "Crear nueva comunidad") }
            Spacer(modifier = Modifier.fillMaxWidth().height(60.dp))
        }
    }
    // Muestra la vista
    CommunitiesBox(showCommunitieBox, onDismiss = { showCommunitieBox = false }) {
    }
}