package com.codigotruko.ucahub.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codigotruko.ucahub.R

@Preview (showBackground = true)
@Composable
fun SearchView () {

    val inputValue = remember { mutableStateOf(TextFieldValue()) }

    Column {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            elevation = 8.dp
        ) {
            Row (modifier = Modifier.fillMaxWidth()) {
                TextField(value = inputValue.value, onValueChange = { inputValue.value = it },
                    label = { Text(text = "Buscar") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    ),
                    leadingIcon = { Icon(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = "Icono Buscar"
                    ) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp))
            }
        }
        LazyColumn() {
            //TODO : Aca ira el historial de contenido que se busque.
        }
    }

}