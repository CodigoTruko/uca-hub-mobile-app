package com.codigotruko.ucahub.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codigotruko.ucahub.data.db.models.Comment
import com.codigotruko.ucahub.data.db.models.Publication

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Comment>)

    @Query("SELECT * FROM comments")
    fun pagingSource(): PagingSource<Int, Comment>

    @Query("DELETE FROM comments")
    suspend fun clearAll()
}