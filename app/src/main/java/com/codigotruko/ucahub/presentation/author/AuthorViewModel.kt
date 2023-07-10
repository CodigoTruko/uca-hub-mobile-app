package com.codigotruko.ucahub.presentation.author

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.data.db.models.Author
import com.codigotruko.ucahub.data.db.models.ProfileResponse
import com.codigotruko.ucahub.data.db.models.Publication
import com.codigotruko.ucahub.repository.AuthorRepository
import com.codigotruko.ucahub.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
class AuthorViewModel (private val authorRepository: AuthorRepository, private val token: String, private val text: String) : ViewModel() {


    private val _authors = MutableStateFlow<Flow<PagingData<Author>>>(emptyFlow())
    var authors: Flow<PagingData<Author>> = _authors.flatMapLatest { it }


    init {
        viewModelScope.launch {
            val result = authorRepository.getAuthorPage(100, token, text)
            authors = result

        }
    }

    fun refreshAuthors(newText: String) {
        viewModelScope.launch {
            val refreshedAuthors = authorRepository.getAuthorPage(100, token, newText)
            authors  = refreshedAuthors

        }
    }
/*
*     companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UcaHubApplication
                AuthorViewModel(app.authorRepository, app.getToken(), "")
            }
        }
    }
*
*
* */
}


@Suppress("UNCHECKED_CAST")
class AuthorViewModelFactory(
    private val authorRepository: AuthorRepository,
    private val token: String,
    private val text: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthorViewModel(authorRepository, token, text) as T
    }
}