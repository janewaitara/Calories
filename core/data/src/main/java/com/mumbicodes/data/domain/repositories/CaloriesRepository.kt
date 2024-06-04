package com.mumbicodes.data.domain.repositories

import com.mumbicodes.data.domain.model.Calorie
import com.mumbicodes.data.domain.model.DataResult

interface CaloriesRepository {

    suspend fun searchCalories(query: String): DataResult<List<Calorie>>
}