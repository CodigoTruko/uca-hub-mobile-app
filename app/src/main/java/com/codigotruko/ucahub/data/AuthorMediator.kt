package com.codigotruko.ucahub.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.codigotruko.ucahub.data.db.PublicationAppDatabase
import com.codigotruko.ucahub.data.db.models.Author
import com.codigotruko.ucahub.data.db.models.RemoteKey
import com.codigotruko.ucahub.data.network.response.AuthorListResponse
import com.codigotruko.ucahub.data.network.service.UcaHubService
import retrofit2.HttpException
import java.io.IOException

@OptIn (ExperimentalPagingApi::class)
class AuthorMediator (
    private val token: String,
    private val text: String,
    private val request: String,
    private val database: PublicationAppDatabase,
    private val ucaHubService: UcaHubService
): RemoteMediator<Int, Author>(){

    private var remoteKeyDao = database.remoteKeyDao()
    private var authorDao = database.authorDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Author>
    ): MediatorResult {
        try {
            // offset
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.remoteKeyByQuery("all")
                    }
                    if (remoteKey.nextKey == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    remoteKey.nextKey
                }
            }

            val response: AuthorListResponse? = when(request)
            {
                "search" -> ucaHubService.getUserSearch(
                    token,
                    state.config.pageSize,
                    loadKey,
                    text
                )
                "follows" -> ucaHubService.getUserFollows(
                    token,
                    state.config.pageSize,
                    loadKey
                )
                "followers" -> ucaHubService.getUserFollowers(
                    token,
                    state.config.pageSize,
                    loadKey
                )
                else -> null
            }




            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    authorDao.clearAll()
                    remoteKeyDao.remoteKeyByQuery("all")
                }

                if (response != null) {
                    authorDao.insertAll(response.results)
                }
                if (response != null) {
                    remoteKeyDao.insertOrReplace(
                        RemoteKey("all", getOffset(response.next))
                    )
                }
            }

            return if (response != null) {
                MediatorResult.Success(
                    endOfPaginationReached = response.next == null
                )
            } else {
                MediatorResult.Error(NullPointerException("Response is null"))
            }

        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        }
    }


    private fun getOffset(nextUrl: String?): Int? {
        val url = nextUrl ?: return null
        val pattern = Regex(".+offset=(\\d+).*").find(url)!!
        val (offset) = pattern.destructured
        return offset.toInt()
    }

}