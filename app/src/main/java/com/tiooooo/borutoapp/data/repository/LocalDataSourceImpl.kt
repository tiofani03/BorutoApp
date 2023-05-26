package com.tiooooo.borutoapp.data.repository

import com.tiooooo.borutoapp.data.local.BorutoDatabase
import com.tiooooo.borutoapp.domain.data_source.LocalDataSource
import com.tiooooo.borutoapp.domain.model.Hero

class LocalDataSourceImpl(
    borutoDatabase: BorutoDatabase,
) : LocalDataSource {
    private val heroDao = borutoDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId)
    }
}
