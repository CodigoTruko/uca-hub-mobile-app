package com.codigotruko.ucahub.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PublicationDao {
    @Upsert
    suspend fun upsertAll(users: List<PublicationEntity>)

    @Query("SELECT * FROM publicationentity")
    fun pagingSource(): PagingSource<Int, PublicationEntity>

    @Query("DELETE FROM publicationentity")
    suspend fun clearAll()
}