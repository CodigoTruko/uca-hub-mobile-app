package com.codigotruko.ucahub.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.codigotruko.ucahub.data.db.PublicationAppDatabase
import com.codigotruko.ucahub.data.db.models.Publication

@OptIn (ExperimentalPagingApi::class)
class PublicationMediator (

    private val database: PublicationAppDatabase,
   // private val PublicationService: PublicationService

): RemoteMediator<Int, Publication>(){
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Publication>
    ): MediatorResult {
        TODO("Not yet implemented")
    }

}