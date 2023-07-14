package com.codigotruko.ucahub.presentation.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import com.codigotruko.ucahub.UcaHubApplication
import com.codigotruko.ucahub.data.db.models.Publication
import com.codigotruko.ucahub.repository.PublicationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

