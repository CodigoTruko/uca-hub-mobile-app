package com.codigotruko.ucahub.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "programs")
data class Program(
    @PrimaryKey var _id: String,
    @SerializedName("name") var name: String,
    @SerializedName("faculty") var faculty: List<Faculty>
)