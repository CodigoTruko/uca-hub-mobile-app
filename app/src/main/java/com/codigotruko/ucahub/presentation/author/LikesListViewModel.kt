package com.codigotruko.ucahub.presentation.author

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.codigotruko.ucahub.data.db.models.Author
import com.codigotruko.ucahub.repository.AuthorRepository
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class LikesListViewModel (private val authorRepository: AuthorRepository, private val token: String) : ViewModel() {


    fun getLikesAuthors(idEvent: String): Flow<PagingData<Author>>? {

        return authorRepository.getLikesPage(100, token, idEvent)
    }



}


@Suppress("UNCHECKED_CAST")
class LikesListViewModelFactory(
    private val authorRepository: AuthorRepository,
    private val token: String,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LikesListViewModel(authorRepository, token) as T
    }
}