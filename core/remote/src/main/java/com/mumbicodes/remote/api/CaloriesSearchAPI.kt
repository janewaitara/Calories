package com.mumbicodes.remote.api

import com.mumbicodes.remote.dto.NutritionDTO
import com.mumbicodes.remote.helpers.NetworkResult

interface CaloriesSearchAPI {
    suspend fun searchCaloriesApi(
        query: String
    ): NetworkResult<NutritionDTO>
}