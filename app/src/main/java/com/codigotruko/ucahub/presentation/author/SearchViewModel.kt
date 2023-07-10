package com.codigotruko.ucahub.presentation.author

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.codigotruko.ucahub.data.db.models.Author
import com.codigotruko.ucahub.repository.AuthorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
class SearchViewModel (private val authorRepository: AuthorRepository, private val token: String, private val text: String) : ViewModel() {


    private val _authors = MutableStateFlow<Flow<PagingData<Author>>>(emptyFlow())
    var authors: Flow<PagingData<Author>> = _authors.flatMapLatest { it }


    init {
        viewModelScope.launch {
            val result = authorRepository.getSearchPage(100, token, text)
            authors = result

        }
    }

    fun refreshAuthors(newText: String) {
        viewModelScope.launch {
            val refreshedAuthors = authorRepository.getSearchPage(100, token, newText)
            authors  = refreshedAuthors

        }
    }

}


@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory(
    private val authorRepository: AuthorRepository,
    private val token: String,
    private val text: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(authorRepository, token, text) as T
    }
}