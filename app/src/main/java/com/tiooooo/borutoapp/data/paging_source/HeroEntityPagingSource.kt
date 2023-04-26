package com.tiooooo.borutoapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tiooooo.borutoapp.data.local.dao.HeroDao
import com.tiooooo.borutoapp.data.mapper.toHero
import com.tiooooo.borutoapp.domain.model.Hero

class HeroEntityPagingSource(
    private val heroDao: HeroDao
) : PagingSource<Int, Hero>() {
    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        val page = params.key ?: 0
        val heroEntities = heroDao.getAllHeroes()
        val heroes = heroEntities.map { heroEntity ->
            heroEntity.toHero()
        }

        return LoadResult.Page(
            data = heroes,
            prevKey = if (page == 0) null else page - 1,
            nextKey = if (heroes.isEmpty()) null else page + 1
        )
    }
}
