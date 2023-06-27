package com.codigotruko.ucahub.di

import com.codigotruko.ucahub.data.remote.PublicationMediator
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.codigotruko.ucahub.data.local.PublicationAppDatabase
import com.codigotruko.ucahub.data.local.PublicationEntity
import com.codigotruko.ucahub.data.remote.UcaHubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePublicationDatabase(@ApplicationContext context: Context): PublicationAppDatabase {
        return Room.databaseBuilder(
            context,
            PublicationAppDatabase::class.java,
            "publications.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUcaHubApi(): UcaHubApi {
        return Retrofit.Builder()
            .baseUrl(UcaHubApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providePublicationPager(publicationDb: PublicationAppDatabase, ucaHubApi: UcaHubApi): Pager<Int, PublicationEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PublicationMediator(
                publicationDb = publicationDb,
                ucaHubApi = ucaHubApi
            ),
            pagingSourceFactory = {
                publicationDb.dao.pagingSource()
            }
        )
    }
}