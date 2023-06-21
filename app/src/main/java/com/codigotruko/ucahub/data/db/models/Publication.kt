package com.codigotruko.ucahub.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "publications")
data class Publication (

    @PrimaryKey var id: Int,

    @SerializedName("user") var user: String,

    //@SerializedName("urlUser") var image: String //Imagen

    @SerializedName("description") var description: String

    //@SerializedName("urlPublication") var image: String //Imagen

        )