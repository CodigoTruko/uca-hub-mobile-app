package com.codigotruko.ucahub.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.lightBlueBackground


@Preview (showBackground = true)
@Composable
fun FloatingButton () {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 66.dp)
    ) {

        FloatingActionButton(
            onClick = { /*TODO*/ },
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
}