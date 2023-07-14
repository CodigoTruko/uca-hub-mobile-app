package com.codigotruko.ucahub.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.codigotruko.ucahub.data.db.PublicationAppDatabase
import com.codigotruko.ucahub.data.db.models.Publication
import com.codigotruko.ucahub.data.db.models.RemoteKey
import com.codigotruko.ucahub.data.network.response.PublicationListResponse
import com.codigotruko.ucahub.data.network.service.UcaHubService
import retrofit2.HttpException
import java.io.IOException

@OptIn (ExperimentalPagingApi::class)
class PublicationMediator (
    private val token: String,
    private val userName: String,
    private val database: PublicationAppDatabase,
    private val ucaHubService: UcaHubService,
): RemoteMediator<Int, Publication>(){

    private var remoteKeyDao = database.remoteKeyDao()
    private var publicationDao = database.publicationDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Publication>
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
            val response = ucaHubService.getFeedPublications(
                    token,
                    state.config.pageSize,
                    loadKey
                )


            response.results.forEach { publication ->
                var offset = 0
                publication.isLiked = false
                do {
                    val likes = ucaHubService.getPublicationLikes(token, publication._id, 100, offset)
                    offset = response.next?.toInt() ?: -1
                    var author = likes.results.find {
                        Log.d("publi USERNAME", it.username)
                        Log.d("My USerNAME", userName)
                        it.username == userName
                    }
                    if (author != null) {
                        publication.isLiked = true
                    }
                    Log.d("PASA", publication.isLiked.toString())
                } while (offset != -1)

            }

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    publicationDao.clearAll()
                    remoteKeyDao.remoteKeyByQuery("all")
                }

                if (response != null) {
                    publicationDao.insertAll(response.results)
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