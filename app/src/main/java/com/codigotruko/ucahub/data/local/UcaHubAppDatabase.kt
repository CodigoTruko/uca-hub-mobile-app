package com.codigotruko.ucahub.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PublicationEntity::class], version = 1)
abstract class UcaHubAppDatabase : RoomDatabase() {

    abstract val publicationDao: PublicationDao

}