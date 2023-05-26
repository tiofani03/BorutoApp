package com.tiooooo.borutoapp.domain.data_source

import com.tiooooo.borutoapp.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
}
