package com.tiooooo.borutoapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.tiooooo.borutoapp.domain.repository.DataStoreOperations
import com.tiooooo.borutoapp.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.BORUTO_PREFERENCES_NAME)

class DataStoreOperationsImpl(
    context: Context,
) : DataStoreOperations {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(Constants.PREFERENCES_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKey.onBoardingKey] ?: false
        }
    }
}
