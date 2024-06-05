package com.mumbicodes.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mumbicodes.local.model.RecentSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.mapLatest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RecentSearchesPreferencesImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : RecentSearchesPreferences {

    data object Keys {
        val searchedParams = stringPreferencesKey("SEARCHED_WORDS")
    }

    override val recentSearches: Flow<List<RecentSearch>>
        get() = getSearchedParamsList()

    private fun getSearchedParamsList(): Flow<List<RecentSearch>> = dataStore.data.mapLatest {
        val json = it[Keys.searchedParams] ?: return@mapLatest emptyList()
        Json.decodeFromString(json)
    }

    /**
     * Adds a new search param
     * when the number of params is 10, remove a param to accommodate the new one using FIFO
     * when a param is added again, remove it from the previous index and add afresh
     * */
    override suspend fun addSearchParam(searchParam: String) {
        val entity = RecentSearch(searchParam = searchParam)
        val list = getSearchedParamsList().firstOrNull()?.toMutableList() ?: mutableListOf()
        when {
            list.count() == 10 -> {
                list.removeAt(0)
                list.add(entity)
                updateSearchedParams(list)
            }

            list.any { it.searchParam.equals(searchParam, true) } -> {
                val index = list.indexOfFirst { it.searchParam.equals(searchParam, true) }
                list.removeAt(index)
                list.add(entity)
                updateSearchedParams(list)
            }

            else -> {
                list.add(entity)
                updateSearchedParams(list)
            }
        }
    }

    private suspend fun updateSearchedParams(list: List<RecentSearch>) {
        dataStore.edit {
            val json = Json.encodeToString(list)
            it[Keys.searchedParams] = json
        }
    }
}