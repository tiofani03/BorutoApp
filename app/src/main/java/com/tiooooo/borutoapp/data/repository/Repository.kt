package com.tiooooo.borutoapp.data.repository

import com.tiooooo.borutoapp.domain.repository.DataStoreOperations
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class Repository @Inject constructor(
    private val dataStore: DataStoreOperations
) {
    suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.saveOnBoardingState(completed)
    }

    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.readOnBoardingState()
    }
}
