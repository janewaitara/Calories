package com.mumbicodes.data.domain.repositories

import com.mumbicodes.data.domain.model.Calorie
import com.mumbicodes.data.domain.model.DataResult
import kotlinx.coroutines.flow.Flow

interface CaloriesRepository {

    val recentSearches: Flow<List<String>>

    suspend fun searchCalories(query: String): DataResult<List<Calorie>>
}