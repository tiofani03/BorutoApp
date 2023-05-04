package com.tiooooo.borutoapp.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.tiooooo.borutoapp.data.repository.Repository
import com.tiooooo.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Hero>> = repository.getAllHeroes()
}
