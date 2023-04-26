package com.tiooooo.borutoapp.data.repository

import androidx.paging.PagingData
import com.tiooooo.borutoapp.domain.data_source.RemoteDataSource
import com.tiooooo.borutoapp.domain.model.Hero
import com.tiooooo.borutoapp.domain.repository.DataStoreOperations
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations,
    private val remoteDataSource: RemoteDataSource,
) {

    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remoteDataSource.getAllHeroes()
    }

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}
