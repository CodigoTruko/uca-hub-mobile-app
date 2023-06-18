package com.codigotruko.ucahub.ui.view.fragments

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun iconFragment(icon: String): ImageVector {
    var result: ImageVector = Icons.Filled.Home

    when(icon){
        "calendar" ->
            result = Icons.Filled.DateRange

        "edit" ->
            result = Icons.Filled.Edit

        "home" ->
            result = Icons.Filled.Home

        "close" ->
            result = Icons.Filled.Close

        "done" ->
            result = Icons.Filled.Done

        "search" ->
            result = Icons.Filled.Search

        "send" ->
            result = Icons.Filled.Send

        "profile" ->
            result = Icons.Filled.Person


    }

    return result
}