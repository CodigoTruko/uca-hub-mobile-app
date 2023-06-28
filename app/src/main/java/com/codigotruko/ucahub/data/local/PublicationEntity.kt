package com.codigotruko.ucahub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PublicationEntity (

    //@PrimaryKey var id: Int,

    //@SerializedName("user") var user: String,

    //@SerializedName("urlUser") var image: String //Imagen

    //@SerializedName("description") var description: String

    //@SerializedName("urlPublication") var image: String //Imagen
    @PrimaryKey
    val id: Int,
    val name: String,
    val tagline: String,
    val description: String,
    val firstBrewed: String,
    val imageUrl: String?
)