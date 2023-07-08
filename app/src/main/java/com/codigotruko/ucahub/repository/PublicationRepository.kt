package com.codigotruko.ucahub.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.codigotruko.ucahub.data.PublicationMediator
import com.codigotruko.ucahub.data.db.PublicationAppDatabase
import com.codigotruko.ucahub.data.network.service.UcaHubService

class PublicationRepository (
    private val database: PublicationAppDatabase,
    private val ucaHubService: UcaHubService
) {
    private val publicationDao = database.publicationDao()

    @ExperimentalPagingApi
    fun getPublicationPage(pageSize: Int, token: String, owner: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        remoteMediator = PublicationMediator(token, owner, database, ucaHubService)
    ) {
        publicationDao.pagingSource()
    }.flow

}