package com.tiooooo.borutoapp.data.repository

import androidx.paging.PagingData
import com.tiooooo.borutoapp.domain.data_source.LocalDataSource
import com.tiooooo.borutoapp.domain.data_source.RemoteDataSource
import com.tiooooo.borutoapp.domain.model.Hero
import com.tiooooo.borutoapp.domain.repository.DataStoreOperations
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) {

    fun getAllHeroes(): Flow<PagingData<Hero>> {
        return remoteDataSource.getAllHeroes()
    }

    fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return remoteDataSource.searchHeroes(query)
    }

    suspend fun getSelectedHero(heroId: Int): Hero = localDataSource.getSelectedHero(heroId)

    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}
