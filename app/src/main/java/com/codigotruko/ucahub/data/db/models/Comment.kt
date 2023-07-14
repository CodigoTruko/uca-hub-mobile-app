package com.codigotruko.ucahub.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "comments")
class Comment (
    @PrimaryKey var _id: String,
    @SerializedName("message") var message: String,
    @SerializedName("author") var author: List<Author>

)