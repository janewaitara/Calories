package com.mumbicodes.data.aggregator.di

import com.mumbicodes.data.aggregator.repositories.CaloriesRepositoryImpl
import com.mumbicodes.data.domain.repositories.CaloriesRepository
import com.mumbicodes.remote.api.CaloriesSearchAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesCaloriesRepository(caloriesSearchAPI: CaloriesSearchAPI): CaloriesRepository =
        CaloriesRepositoryImpl(caloriesSearchAPI)
}