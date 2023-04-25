package com.tiooooo.borutoapp.domain.use_cases.read_onboarding

import com.tiooooo.borutoapp.data.repository.Repository

class ReadOnBoardingUseCase(
    private val repository: Repository
) {
    operator fun invoke() = repository.readOnBoardingState()
}

