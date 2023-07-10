package com.codigotruko.ucahub.ui.views.bottombarviews

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.codigotruko.ucahub.R
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.presentation.author.AuthorViewModel
import com.codigotruko.ucahub.presentation.author.AuthorViewModelFactory
import com.codigotruko.ucahub.presentation.profile.MyProfileViewModel
import com.codigotruko.ucahub.presentation.profile.ProfileViewModel
import com.codigotruko.ucahub.presentation.profile.ProfileViewModelFactory
import com.codigotruko.ucahub.ui.theme.darkWhiteBackground
import com.codigotruko.ucahub.ui.views.publication.PublicationItem

@Composable
fun SearchView (navController: NavHostController) {

    val inputValue = remember { mutableStateOf(TextFieldValue()) }

    val context = LocalContext.current

    val app = context.applicationContext as UcaHubApplication

    val authorViewModelFactory = AuthorViewModelFactory(app.authorRepository, app.getToken(), inputValue.value.text)
    val authorViewModel: AuthorViewModel = viewModel(factory = authorViewModelFactory)
    val authors = authorViewModel.authors.collectAsLazyPagingItems()


    val myProfileViewModel: MyProfileViewModel = viewModel(factory = MyProfileViewModel.Factory)
    val myProfile by myProfileViewModel.myProfileResponse.collectAsState()


    LaunchedEffect(key1 = authors.loadState) {
        if(authors.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (authors.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }


    Column {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            elevation = 8.dp
        ) {
            Row (modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = inputValue.value,
                    onValueChange = {
                        inputValue.value = it
                        authorViewModel.refreshAuthors(inputValue.value.text)
                        authors.refresh()
                                    },
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
        LazyColumn(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            items(authors){author ->
                if(authors.loadState.refresh is LoadState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                else{
                    if (author != null) {
                        if(myProfile?.profile?.username != author.username)
                            SearchCard(navController, author.username)
                    }
                }
            }
            item {
                if(authors.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator()
                }
            }
            item {

                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp))
            }
        }
    }

}
@Composable
fun SearchCard(navController: NavHostController, text: String ) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .clickable {
            navController.navigate("anotherUser_profile/${text}")
        },
        backgroundColor = darkWhiteBackground,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.imagen_perfil_prueba),
                contentDescription = "Imagen de perfil",
                modifier = Modifier.width(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                fontWeight = FontWeight.Medium,
                fontSize = 25.sp
            )
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}