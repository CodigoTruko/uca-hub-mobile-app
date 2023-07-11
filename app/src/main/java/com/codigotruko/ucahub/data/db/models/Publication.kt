package com.codigotruko.ucahub.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "publications")
data class Publication (

    //@PrimaryKey var id: Int,

    //@SerializedName("user") var user: String,

    @PrimaryKey var _id: String,

    @SerializedName("title") var title: String,

    @SerializedName("description") var description: String,

    @SerializedName("author") var author: List<Author>,

    @SerializedName("likes") var likes: Int,

    @SerializedName("comments") var comments: Int,

    //@SerializedName("url") var image: String
        )