package com.codigotruko.ucahub.ui.views.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.lightBlueBackground
import com.codigotruko.ucahub.ui.views.overlapelements.AddEditPublicationBox


@Composable
fun FloatingButton (_aux: Boolean, placeRoute: String, action: () -> Unit = {}){

    var showAddPubliBox by rememberSaveable() { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 70.dp)
    ) {

        FloatingActionButton(
            onClick = { showAddPubliBox = true },
            shape = RoundedCornerShape(100),
            containerColor = lightBlueBackground
        ) {

            Icon(painter = painterResource(
                id = R.drawable.add_icon),
                modifier = Modifier.height(50.dp),
                contentDescription = "Boton para agregar publicaciones", tint = Color.White
            )
        }

    }

    AddEditPublicationBox(showAddPubliBox, onDismiss = { showAddPubliBox = false },true, null, _aux, placeRoute, action )

}