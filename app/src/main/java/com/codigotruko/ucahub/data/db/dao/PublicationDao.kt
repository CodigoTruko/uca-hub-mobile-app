package com.codigotruko.ucahub.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PublicationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<PublicationDao>)

    @Query("SELECT * FROM publications")
    fun pagingSource(): PagingSource<Int, PublicationDao>

    @Query("DELETE FROM publications")
    suspend fun clearAll()
}