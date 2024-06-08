package com.mumbicodes.domain.repositories

import com.mumbicodes.domain.model.Calorie
import com.mumbicodes.domain.model.DataResult
import kotlinx.coroutines.flow.Flow

interface CaloriesRepository {

    val recentSearches: Flow<List<String>>

    suspend fun searchCalories(query: String): DataResult<List<Calorie>>
}