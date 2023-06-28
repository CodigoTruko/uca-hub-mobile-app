package com.codigotruko.ucahub.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.codigotruko.ucahub.data.local.UcaHubAppDatabase
import com.codigotruko.ucahub.data.local.PublicationEntity
import com.codigotruko.ucahub.data.mappers.toPublicationEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PublicationMediator(
    private val publicationDb: UcaHubAppDatabase,
    private val ucaHubApi: UcaHubApi
): RemoteMediator<Int, PublicationEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PublicationEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val publications = ucaHubApi.getPublications(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            publicationDb.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    publicationDb.publicationDao.clearAll()
                }
                val publicationEntities = publications.map { it.toPublicationEntity() }
                publicationDb.publicationDao.upsertAll(publicationEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = publications.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}