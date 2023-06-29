package com.codigotruko.ucahub.ui.view

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.ui.theme.blueBackground
import com.codigotruko.ucahub.ui.view.fragments.FloatingButton
import com.codigotruko.ucahub.ui.theme.mainBackground
import com.codigotruko.ucahub.ui.view.fragments.ButtonNormalFragment
import com.codigotruko.ucahub.ui.view.fragments.ImageUCAHUB


@Composable
fun ProfileView(navController: NavHostController, userName: String?, carnet: String?, faculty: String?, carrer: String?, description: String?, userID: String){

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = mainBackground)
    ){
        item{

            ImageUCAHUB()

            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(blueBackground),
                shape = RoundedCornerShape(8.dp)) {
                Icon(painter = painterResource(id = R.drawable.edit_icon),
                    tint = Color.White,
                    contentDescription = "Icono editar perfil.")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Editar", fontSize = 18.sp)
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = userName.toString(),
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                TextProfileFragment(name = "Carnet", value = carnet)

                TextProfileFragment(name = "Facultad", value = faculty)

                TextProfileFragment(name = "Carrera", value = carrer)

                TextProfileFragment(name = "Descripción", value = description)


            }


            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp))

        }
    }
    FloatingButton()
}

@Composable
fun TextProfileFragment(name: String?, value: String?){
    Column{
        Text(
            text = name.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value.toString(),
            fontSize = 20.sp
        )
    }
}

/*
private val _publications = MutableStateFlow(emptyFlow<PagingData<Publication>>())

val publications: StateFlow<Flow<PagingData<Publication>>> = _publications

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublicationListUi(publications: Flow<PagingData<Publication>>) {
    Scaffold(
        content = { it ->
            PublicationContent(
                publications = publications,
                onSelected = { publication -> Log.e("WordsContent",
                    "Selected: $publication") }
            )
        }
    )
}

@Composable
private fun PublicationContent(
    publications: Flow<PagingData<Publication>>,
    onSelected: (Publication) -> Unit,
) {
    // 1
    val items: LazyPagingItems<Publication> = publications.collectAsLazyPagingItems()
    LazyColumn {
        // 2
        items(items = items) { publication ->
            // 3
            if (publications != null) {
                PublicationColumnItem(
                    publication = publication
                ) { onSelected(publication) }
            }
        }
    }
}

@Composable
private fun PublicationColumnItem(
    publication: Publication,
    onClick: () -> Unit,
) {
    Row(                                              // 1
        modifier = Modifier.clickable { onClick() },    // 2
    ) {
        Text(
            modifier = Modifier.padding(16.dp),           // 3
            text = publication.name,                            // 4
        )
    }
}

*/