package com.tiooooo.borutoapp.domain.use_cases.get_selected_hero

import com.tiooooo.borutoapp.data.repository.Repository
import com.tiooooo.borutoapp.domain.model.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int): Hero = repository.getSelectedHero(heroId)
}
