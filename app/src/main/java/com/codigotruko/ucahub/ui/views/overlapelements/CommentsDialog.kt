package com.codigotruko.ucahub.ui.views.overlapelements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.data.db.models.Author
import com.codigotruko.ucahub.data.db.models.Comment
import com.codigotruko.ucahub.ui.theme.commentBackground
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


@Composable
fun CommentsBox(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit, comments: Flow<PagingData<Comment>>, publicationid: String) {

    val comments = comments.collectAsLazyPagingItems()
    val app = LocalContext.current.applicationContext as UcaHubApplication
    val scope = CoroutineScope(Dispatchers.Main)

    if (show) {
        val inputValue = remember { mutableStateOf(TextFieldValue()) }

        Dialog(onDismissRequest = { onDismiss() }) {
                Card(
                    colors = CardDefaults.cardColors(darkWhiteBackground),
                    modifier = Modifier
                        .height(600.dp)
                        .width(450.dp)
                ) {
                    Text(
                        text = "Comentarios",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(490.dp)
                    ) {
                        items(comments) {comment ->
                            if (comment != null) {
                                commentItem(comment)
                            }

                        }
                    }

                    Row(modifier = Modifier.fillMaxWidth()) {
                        TextField(
                            value = inputValue.value, onValueChange = { inputValue.value = it },
                            placeholder = {
                                Text(
                                    text = "Comentar publicación",
                                    fontSize = 15.sp,
                                    modifier = Modifier.fillMaxSize()
                                )
                            },
                            leadingIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        app.createComment(publicationid, inputValue.value.text)
                                    }
                                    onConfirm()
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.send_icon),
                                        contentDescription = "Icono Send"
                                    )
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                        )
                    }

                }
        }
    }
}

@Composable
fun commentItem(comment: Comment){
    Card(
        colors = CardDefaults.cardColors(commentBackground),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.imagen_perfil_prueba),
                contentDescription = "Imagen de perfil",
                modifier = Modifier.width(25.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = comment.author[0].username,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp
            )
        }
        Text(
            text =  comment.message,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}