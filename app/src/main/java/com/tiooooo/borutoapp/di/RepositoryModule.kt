package com.tiooooo.borutoapp.di

import android.content.Context
import com.tiooooo.borutoapp.data.repository.DataStoreOperationsImpl
import com.tiooooo.borutoapp.data.repository.Repository
import com.tiooooo.borutoapp.domain.repository.DataStoreOperations
import com.tiooooo.borutoapp.domain.use_cases.UseCases
import com.tiooooo.borutoapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.tiooooo.borutoapp.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.tiooooo.borutoapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.tiooooo.borutoapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.tiooooo.borutoapp.domain.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperation(
        @ApplicationContext context: Context,
    ): DataStoreOperations {
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(
        repository: Repository
    ): UseCases {
        return UseCases(
            saveOnBoardingUseCase = SaveOnBoardingUseCase(repository = repository),
            readOnBoardingUseCase = ReadOnBoardingUseCase(repository = repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository = repository),
            searchHeroesUseCase = SearchHeroesUseCase(repository = repository),
            getSelectedHeroUseCase = GetSelectedHeroUseCase(repository = repository),
        )
    }
}
