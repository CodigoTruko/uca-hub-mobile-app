package com.codigotruko.ucahub.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "profiles")
data class Profile(

    @PrimaryKey var id: String,
    @SerializedName("name") var name: String,
    @SerializedName("carnet") var carnet: String,
    @SerializedName("username") var username: String,
    @SerializedName("email") var email: String,
    @SerializedName("program") var program: List<Program>,
    @SerializedName("description") var description: String?,
    @SerializedName("followers") var followers: String,
    @SerializedName("follows") var follows: String,

)