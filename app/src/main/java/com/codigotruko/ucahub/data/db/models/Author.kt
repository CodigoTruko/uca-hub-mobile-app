package com.codigotruko.ucahub.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "authors")
data class Author(
    @PrimaryKey var _id: String,
    @SerializedName("name") var name: String,
    @SerializedName("username") var username: String,
    @SerializedName("carnet") var carnet: String
)