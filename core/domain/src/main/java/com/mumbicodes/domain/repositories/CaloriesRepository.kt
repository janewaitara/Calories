package com.mumbicodes.domain.repositories

import com.mumbicodes.domain.model.Calorie
import com.mumbicodes.domain.model.DataResult
import kotlinx.coroutines.flow.Flow

/**
 * An interface that defines all the functions needed to fetch data from the sources
 * */
interface CaloriesRepository {

    /***
     * Holds all the recent searches from local storage
     */
    val recentSearches: Flow<List<String>>

    /**
     * searches for a specific[query] food nutrition
     * */
    suspend fun searchCalories(query: String): DataResult<List<Calorie>>
}