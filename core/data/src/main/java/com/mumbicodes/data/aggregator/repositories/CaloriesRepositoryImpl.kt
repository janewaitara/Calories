package com.mumbicodes.data.aggregator.repositories

import com.mumbicodes.data.aggregator.mappers.toDomain
import com.mumbicodes.data.domain.model.Calorie
import com.mumbicodes.data.domain.model.DataResult
import com.mumbicodes.data.domain.repositories.CaloriesRepository
import com.mumbicodes.local.preferences.RecentSearchesPreferences
import com.mumbicodes.remote.api.CaloriesSearchAPI
import com.mumbicodes.remote.helpers.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CaloriesRepositoryImpl @Inject constructor(
    private val caloriesSearchAPI: CaloriesSearchAPI,
    private val recentSearchedPreferences: RecentSearchesPreferences
) : CaloriesRepository {

    override val recentSearches: Flow<List<String>>
        get() = recentSearchedPreferences.recentSearches.map { recentSearches ->
            recentSearches.sortedByDescending {
                it.timestamp
            }.map { it.searchParam }
        }

    override suspend fun searchCalories(query: String): DataResult<List<Calorie>> {
        recentSearchedPreferences.addSearchParam(searchParam = query)
        return when (val networkResult = caloriesSearchAPI.searchCaloriesApi(query)) {
            is NetworkResult.Success -> {
                val data = networkResult.data.items
                DataResult.Success(
                    data.mapNotNull { it.toDomain() }
                )
            }

            is NetworkResult.Error ->
                DataResult.Error(errorMessage = networkResult.message, exception = networkResult.exception)
        }
    }
}