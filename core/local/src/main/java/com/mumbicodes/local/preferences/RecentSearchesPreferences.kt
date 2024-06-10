package com.mumbicodes.local.preferences

import com.mumbicodes.local.model.RecentSearch
import kotlinx.coroutines.flow.Flow

/**
 * Defines all the functions needed to interact with the recent searches preferences
 * */
interface RecentSearchesPreferences {

    /***
     * Holds all the recent searches from local storage
     */
    val recentSearches: Flow<List<RecentSearch>>

    /***
     * Add a [searchParam] to the recent searches list
     */
    suspend fun addSearchParam(searchParam: String)
}