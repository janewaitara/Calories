package com.mumbicodes.local.preferences

import com.mumbicodes.local.model.RecentSearch
import kotlinx.coroutines.flow.Flow

interface RecentSearchesPreferences {

    val recentSearches: Flow<List<RecentSearch>>

    suspend fun addSearchParam(searchParam: String)
}