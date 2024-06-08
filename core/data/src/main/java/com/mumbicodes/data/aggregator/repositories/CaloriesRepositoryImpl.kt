package com.mumbicodes.data.aggregator.repositories

import com.mumbicodes.data.aggregator.mappers.toDomain
import com.mumbicodes.local.preferences.RecentSearchesPreferences
import com.mumbicodes.remote.api.CaloriesSearchAPI
import com.mumbicodes.remote.helpers.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CaloriesRepositoryImpl @Inject constructor(
    private val caloriesSearchAPI: CaloriesSearchAPI,
    private val recentSearchedPreferences: RecentSearchesPreferences
) : com.mumbicodes.domain.repositories.CaloriesRepository {

    override val recentSearches: Flow<List<String>>
        get() = recentSearchedPreferences.recentSearches.map { recentSearches ->
            recentSearches.sortedByDescending {
                it.timestamp
            }.map { it.searchParam }
        }

    override suspend fun searchCalories(query: String): com.mumbicodes.domain.model.DataResult<List<com.mumbicodes.domain.model.Calorie>> {
        recentSearchedPreferences.addSearchParam(searchParam = query)
        return when (val networkResult = caloriesSearchAPI.searchCaloriesApi(query)) {
            is NetworkResult.Success -> {
                val data = networkResult.data.items
                com.mumbicodes.domain.model.DataResult.Success(
                    data.mapNotNull { it.toDomain() }
                )
            }

            is NetworkResult.Error ->
                com.mumbicodes.domain.model.DataResult.Error(errorMessage = networkResult.message)
        }
    }
}