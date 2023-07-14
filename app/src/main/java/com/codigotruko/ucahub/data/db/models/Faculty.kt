package com.codigotruko.ucahub.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "faculties")
data class Faculty (
    @PrimaryKey var _id: String,
    @SerializedName("name") var name: String
)