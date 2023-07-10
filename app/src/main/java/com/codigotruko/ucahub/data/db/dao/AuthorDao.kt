package com.codigotruko.ucahub.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codigotruko.ucahub.data.db.models.Author

@Dao
interface AuthorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Author>)

    @Query("SELECT * FROM authors")
    fun pagingSource(): PagingSource<Int, Author>

    @Query("DELETE FROM authors")
    suspend fun clearAll()

}