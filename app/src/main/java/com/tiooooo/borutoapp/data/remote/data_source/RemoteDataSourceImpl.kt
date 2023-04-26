package com.tiooooo.borutoapp.data.remote.data_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tiooooo.borutoapp.data.local.BorutoDatabase
import com.tiooooo.borutoapp.data.local.dao.HeroDao
import com.tiooooo.borutoapp.data.paging_source.HeroEntityPagingSource
import com.tiooooo.borutoapp.data.paging_source.HeroRemoteMediator
import com.tiooooo.borutoapp.data.remote.api.BorutoApi
import com.tiooooo.borutoapp.domain.data_source.RemoteDataSource
import com.tiooooo.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val borutoDatabase: BorutoDatabase,
) : RemoteDataSource {

    private val heroDao: HeroDao = borutoDatabase.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { HeroEntityPagingSource(heroDao) }

        return Pager(
            config = PagingConfig(pageSize = 3),
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                borutoDatabase = borutoDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }
}
