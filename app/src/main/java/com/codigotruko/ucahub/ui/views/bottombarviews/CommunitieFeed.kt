package com.codigotruko.ucahub.ui.views.bottombarviews

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.views.fragments.CommunitieCard
import com.codigotruko.ucahub.ui.views.fragments.FloatingButton

@Composable
fun CommunitieFeedView () {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item {
            CommunitieCard(communitieMember = true)

            Button(
                onClick = {/* TODO : Hacer on click para buscar en comunidad. */},
                colors = ButtonDefaults.buttonColors(blueBackground),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(vertical = 16.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.search_icon),
                    tint = Color.White,
                    contentDescription = "Icono buscar.")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Buscar en la comunidad")
            }

            // TODO : IMPLEMENTAR PUBLICATIONS DE LA COMUNIDAD.

        }
    }

    FloatingButton(false, "community")

    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp))
}
