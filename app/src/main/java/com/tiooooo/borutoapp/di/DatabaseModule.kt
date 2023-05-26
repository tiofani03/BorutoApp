package com.tiooooo.borutoapp.di

import android.content.Context
import androidx.room.Room
import com.tiooooo.borutoapp.data.local.BorutoDatabase
import com.tiooooo.borutoapp.data.repository.LocalDataSourceImpl
import com.tiooooo.borutoapp.domain.data_source.LocalDataSource
import com.tiooooo.borutoapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        BorutoDatabase::class.java,
        Constants.BORUTO_DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: BorutoDatabase,
    ): LocalDataSource {
        return LocalDataSourceImpl(
            borutoDatabase = database,
        )
    }
}
