package com.mumbicodes.data.aggregator.di

import com.mumbicodes.data.aggregator.repositories.CaloriesRepositoryImpl
import com.mumbicodes.domain.repositories.CaloriesRepository
import com.mumbicodes.local.preferences.RecentSearchesPreferences
import com.mumbicodes.remote.api.CaloriesSearchAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesCaloriesRepository(
        caloriesSearchAPI: CaloriesSearchAPI,
        recentSearchesPreferences: RecentSearchesPreferences
    ): CaloriesRepository =
        CaloriesRepositoryImpl(caloriesSearchAPI, recentSearchesPreferences)
}