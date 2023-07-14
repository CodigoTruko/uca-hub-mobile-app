package com.codigotruko.ucahub.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.codigotruko.ucahub.data.CommentMediator
import com.codigotruko.ucahub.data.PublicationMediator
import com.codigotruko.ucahub.data.PublicationProfileMediator
import com.codigotruko.ucahub.data.db.PublicationAppDatabase
import com.codigotruko.ucahub.data.network.service.UcaHubService

class PublicationRepository(
    private val database: PublicationAppDatabase,
    private val ucaHubService: UcaHubService
) {
    private val publicationDao = database.publicationDao()
    private val commentDao = database.commentDao()

    @ExperimentalPagingApi
    fun getPublicationPage(pageSize: Int, token: String, userName: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        remoteMediator = PublicationMediator(token, userName, database, ucaHubService)
    ) {
        publicationDao.pagingSource()
    }.flow


    @ExperimentalPagingApi
    fun getPublicationProfilePage(pageSize: Int, token: String, identifier: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        remoteMediator = PublicationProfileMediator(token, identifier, database, ucaHubService)
    ) {
        publicationDao.pagingSource()
    }.flow

    suspend fun changeLikeState(token: String, idPublication: String){
        ucaHubService.changeStatePublicationLike(token, idPublication)
    }
    suspend fun createComment(token: String, idPublication: String, message:String){
        ucaHubService.createPublicationComment(token, idPublication, message)
    }

    @ExperimentalPagingApi
    fun getCommentPage(pageSize: Int, token: String, idPublication: String) = Pager(
        config = PagingConfig(
            pageSize = pageSize,
            prefetchDistance = (0.10 * pageSize).toInt()
        ),
        remoteMediator = CommentMediator(token, idPublication, database, ucaHubService)
    ) {
        commentDao.pagingSource()
    }.flow

}