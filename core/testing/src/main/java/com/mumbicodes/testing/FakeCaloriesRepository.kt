package com.mumbicodes.testing

import com.mumbicodes.domain.model.Calorie
import com.mumbicodes.domain.model.DataResult
import com.mumbicodes.domain.repositories.CaloriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * This ideally should be in the data module as it is a fake of the CalorieRepository.
 * However, I could not access files in the testing package of the data module in the feature module hence why I created
 * a separate module to access the fake repository from a different module other than the data module
 * */
class FakeCaloriesRepository : CaloriesRepository {

    private val state: MutableStateFlow<List<Calorie>?> = MutableStateFlow(null)

    private fun updateState(list: List<Calorie>?) {
        state.value = list
    }

    fun setResponseToBeSuccessButEmpty() {
        updateState(emptyList())
    }

    fun setResponseToBeSuccessWithData() {
        updateState(
            listOf(
                Calorie(
                    name = "Apple",
                    calories = 95.0,
                    servingSizeGrams = 130.0,
                    fatTotalGrams = 0.3,
                    fatSaturatedGrams = 0.0,
                    proteinGrams = 0.3,
                    sodiumMilligrams = 0.0,
                    potassiumMilligrams = 0.0,
                    cholesterolMilligrams = 0.0,
                    carbohydratesTotalGrams = 25.0,
                    fiberGrams = 4.0,
                    sugarGrams = 19.0
                )
            )
        )
    }

    fun setResponseToBeError() {
        updateState(null)
    }

    fun clearData() {
        state.value = null
    }

    private var _recentSearches: MutableStateFlow<List<String>> = MutableStateFlow(listOf())

    override val recentSearches: Flow<List<String>>
        get() = _recentSearches

    fun updateRecentSearchesState(list: List<String>) {
        _recentSearches.value = list
    }

    override suspend fun searchCalories(query: String): DataResult<List<Calorie>> {
        return if (state.value == null) {
            DataResult.Error(errorMessage = "Failed getting results for calories")
        } else {
            DataResult.Success(data = state.value ?: emptyList())
        }
    }
}