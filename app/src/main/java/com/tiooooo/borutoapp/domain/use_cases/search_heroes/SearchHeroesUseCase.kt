package com.tiooooo.borutoapp.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.tiooooo.borutoapp.data.repository.Repository
import com.tiooooo.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<PagingData<Hero>> {
        return repository.searchHeroes(query)
    }

}
