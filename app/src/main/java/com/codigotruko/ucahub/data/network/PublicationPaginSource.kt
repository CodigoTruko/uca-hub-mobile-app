package com.codigotruko.ucahub.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codigotruko.ucahub.data.db.models.Publication
import com.codigotruko.ucahub.data.network.service.UcaHubService
import retrofit2.HttpException
import java.io.IOException

/*
* class PublicationPagingSource(private val ucahubservice: UcaHubService) :
    PagingSource<Int, Publication>() {

    override fun getRefreshKey(state: PagingState<Int, Publication>): Int? {
        val pageSize = state.config.pageSize
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(pageSize) ?: anchorPage?.nextKey?.minus(pageSize)
        }
    }

    override suspend fun load(params: LoadParams<Int>):
            LoadResult<Int, Publication> {
        return try {
            val nextPage = params.key ?: 0
            val pageSize = params.loadSize
            val publicationsResult = ucahubservice
                .getPublications( "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2NGExYmUxYmNlZDM1ZGU0ZDlkOTQyYmQiLCJ1c2VybmFtZSI6IkZsYW4iLCJpYXQiOjE2ODgzMzU2NDEsImV4cCI6NTE2ODgzMzU2NDF9.Vu3U9mXp-mzTMYlaZcNaHYbmvydsGtoKqgxS1-5CSLI", pageSize, nextPage)
            LoadResult.Page(
                data = publicationsResult.results,
                nextKey = if (publicationsResult.next != null) nextPage + pageSize else null,
                prevKey = if (publicationsResult.previous != null) nextPage - pageSize else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
* */