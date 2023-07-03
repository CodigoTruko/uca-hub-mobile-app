package com.codigotruko.ucahub.data.db

import androidx.room.TypeConverter
import com.codigotruko.ucahub.data.db.models.Author
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromAuthorList(authorList: List<Author>): String {
        val gson = Gson()
        return gson.toJson(authorList)
    }

    @TypeConverter
    fun toAuthorList(authorListString: String): List<Author> {
        val gson = Gson()
        val type = object : TypeToken<List<Author>>() {}.type
        return gson.fromJson(authorListString, type)
    }
}
