package com.mumbicodes.remote

import com.mumbicodes.remote.dto.NutritionDTO
import com.mumbicodes.remote.helpers.NetworkResult
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class CaloriesSearchApiTest {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @Test
    fun `given CaloriesSearchApi, when searching for food calories, should return NetworkResultSuccess`() =
        runTest {
            val caloriesApi = generateFakeCaloriesSearchApiImpl(
                statusCode = HttpStatusCode.OK,
                networkResult = VALID_FOOD_SEARCH_RESULT
            )
            val networkResponse = caloriesApi.searchCaloriesApi(query = "Rice")
            assert(networkResponse is NetworkResult.Success)
        }

    @Test
    fun `given CaloriesSearchApi, when searching for food calories, list should not be empty`() =
        runTest {
            val caloriesApi = generateFakeCaloriesSearchApiImpl(
                statusCode = HttpStatusCode.OK,
                networkResult = VALID_FOOD_SEARCH_RESULT
            )
            val networkResponse = caloriesApi.searchCaloriesApi(query = "Rice")
            assert(networkResponse is NetworkResult.Success)

            val data = json.decodeFromString<NutritionDTO>(VALID_FOOD_SEARCH_RESULT)
            val responseData = (networkResponse as NetworkResult.Success).data
            assertEquals(responseData, data)
        }

    @Test
    fun `given CaloriesSearchApi, when searching for food calories, should return NetworkResultError`() =
        runTest {
            val caloriesApi = generateFakeCaloriesSearchApiImpl(
                statusCode = HttpStatusCode.Unauthorized,
                networkResult = UNAUTHORIZED_FOOD_SEARCH_RESULT
            )
            val networkResponse = caloriesApi.searchCaloriesApi(query = "Rice")
            assert(networkResponse is NetworkResult.Error)
        }
}