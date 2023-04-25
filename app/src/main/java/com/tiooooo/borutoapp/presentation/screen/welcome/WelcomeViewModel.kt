package com.tiooooo.borutoapp.presentation.screen.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiooooo.borutoapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch {
            useCases.saveOnBoardingUseCase(completed)
        }
    }
}
