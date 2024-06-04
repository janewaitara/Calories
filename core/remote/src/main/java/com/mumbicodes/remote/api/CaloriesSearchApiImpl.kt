package com.mumbicodes.remote.api

import com.mumbicodes.remote.dto.NutritionDTO
import com.mumbicodes.remote.helpers.EndPoints
import com.mumbicodes.remote.helpers.NetworkResult
import com.mumbicodes.remote.helpers.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import javax.inject.Inject

class CaloriesSearchApiImpl @Inject constructor(
    private val httpClient: HttpClient
) : CaloriesSearchAPI {

    override suspend fun searchCaloriesApi(query: String): NetworkResult<NutritionDTO> =
        safeApiCall {
            val response = httpClient.get {
                url(urlString = EndPoints.Nutrition.url)
                parameter("query", query)
            }
            response.body()
        }
}