package com.tiooooo.borutoapp.domain.data_source

import androidx.paging.PagingData
import com.tiooooo.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(query: String): Flow<PagingData<Hero>>
}
