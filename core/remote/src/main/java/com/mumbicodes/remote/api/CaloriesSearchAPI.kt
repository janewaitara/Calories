package com.mumbicodes.remote.api

import com.mumbicodes.remote.dto.NutritionDTO
import com.mumbicodes.remote.helpers.NetworkResult

/**
 * An interface that defines all the functions needed to interact with the API
 * */
interface CaloriesSearchAPI {

    /**
     * searches for a specific[query] food nutrition
     * */
    suspend fun searchCaloriesApi(
        query: String
    ): NetworkResult<NutritionDTO>
}