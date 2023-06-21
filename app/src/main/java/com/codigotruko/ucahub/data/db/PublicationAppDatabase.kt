package com.codigotruko.ucahub.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codigotruko.ucahub.data.db.dao.PublicationDao
import com.codigotruko.ucahub.data.db.dao.RemoteKeyDao
import com.codigotruko.ucahub.data.db.models.Publication
import com.codigotruko.ucahub.data.db.models.RemoteKey

@Database(entities = [Publication::class, RemoteKey::class], version = 1, exportSchema = false)
abstract class PublicationAppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PublicationDao
    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {
        private var INSTANCE: PublicationAppDatabase? = null
        fun getInstance(application: Application): PublicationAppDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    PublicationAppDatabase::class.java,
                    "publication_app"
                )
                    .build()
                INSTANCE = instance
                instance
            }
    }

}