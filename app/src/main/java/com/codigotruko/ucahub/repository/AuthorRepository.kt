package com.codigotruko.ucahub.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.codigotruko.ucahub.data.AuthorMediator
import com.codigotruko.ucahub.data.db.PublicationAppDatabase
import com.codigotruko.ucahub.data.network.service.UcaHubService

class AuthorRepository (
    private val database: PublicationAppDatabase,
    private val ucaHubService: UcaHubService
) {
    private val authorDao = database.authorDao()

    @ExperimentalPagingApi
    fun getSearchPage(pageSize: Int, token: String, text: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        remoteMediator = AuthorMediator(token, text, "search", database, ucaHubService)
    ) {
        authorDao.pagingSource()
    }.flow

    @ExperimentalPagingApi
    fun getFollowsPage(pageSize: Int, token: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        remoteMediator = AuthorMediator(token,  "", "follows", database, ucaHubService)
    ) {
        authorDao.pagingSource()
    }.flow

    @ExperimentalPagingApi
    fun getFollowersPage(pageSize: Int, token: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        remoteMediator = AuthorMediator(token, "", "followers", database, ucaHubService)
    ) {
        authorDao.pagingSource()
    }.flow

}