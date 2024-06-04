package com.mumbicodes.data.aggregator.repositories

import com.mumbicodes.data.aggregator.mappers.toDomain
import com.mumbicodes.data.domain.model.Calorie
import com.mumbicodes.data.domain.model.DataResult
import com.mumbicodes.data.domain.repositories.CaloriesRepository
import com.mumbicodes.remote.api.CaloriesSearchAPI
import com.mumbicodes.remote.helpers.NetworkResult
import javax.inject.Inject

class CaloriesRepositoryImpl @Inject constructor(
    private val caloriesSearchAPI: CaloriesSearchAPI
) : CaloriesRepository {

    override suspend fun searchCalories(query: String): DataResult<List<Calorie>> =
        when (val networkResult = caloriesSearchAPI.searchCaloriesApi(query)) {
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