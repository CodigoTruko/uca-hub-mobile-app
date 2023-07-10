package com.codigotruko.ucahub.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codigotruko.ucahub.data.db.dao.AuthorDao
import com.codigotruko.ucahub.data.db.dao.PublicationDao
import com.codigotruko.ucahub.data.db.dao.RemoteKeyDao
import com.codigotruko.ucahub.data.db.models.Author
import com.codigotruko.ucahub.data.db.models.Publication
import com.codigotruko.ucahub.data.db.models.RemoteKey

@Database(entities = [Publication::class, Author::class, RemoteKey::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PublicationAppDatabase : RoomDatabase() {
    abstract fun publicationDao(): PublicationDao
    abstract fun authorDao(): AuthorDao
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
                    .fallbackToDestructiveMigration() //borrar despues
                    .build()
                INSTANCE = instance
                instance
            }
    }

}