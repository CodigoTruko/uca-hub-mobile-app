package com.codigotruko.ucahub.presentation.publication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.codigotruko.ucahub.data.local.PublicationEntity
import com.codigotruko.ucahub.data.mappers.toPublication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PublicationViewModel @Inject constructor(
    pager: Pager<Int, PublicationEntity>
): ViewModel() {

    val publicationPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toPublication() }
        }
        .cachedIn(viewModelScope)
}