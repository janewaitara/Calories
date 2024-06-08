package com.mumbicodes.data.repositories

import com.mumbicodes.data.domain.model.Calorie
import com.mumbicodes.data.domain.model.DataResult
import com.mumbicodes.data.domain.repositories.CaloriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class FakeCalorieRepository : CaloriesRepository {

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

    override val recentSearches: Flow<List<String>>
        get() = MutableStateFlow(listOf())

    override suspend fun searchCalories(query: String): DataResult<List<Calorie>> {
        return if (state.value == null) {
            DataResult.Error(errorMessage = "Failed getting results for calories")
        } else {
            DataResult.Success(data = state.value ?: emptyList())
        }
    }
}

class CalorieRepositoryTest {

    private lateinit var fakeCalorieRepository: FakeCalorieRepository

    @Before
    fun setup() {
        fakeCalorieRepository = FakeCalorieRepository()
    }

    @After
    fun tearDown() {
        fakeCalorieRepository.clearData()
    }

    @Test
    fun `given CaloriesRepository, when searching for food calories, should return DataResultError`() = runTest {
        fakeCalorieRepository.setResponseToBeError()
        val caloriesResult = fakeCalorieRepository.searchCalories("rice")
        assert(caloriesResult is DataResult.Error)
    }

    @Test
    fun `given CaloriesRepository, when searching for food calories, should return DataResultSuccess with Data`() = runTest {
        fakeCalorieRepository.setResponseToBeSuccessWithData()
        val caloriesResult = fakeCalorieRepository.searchCalories("Apple")
        assert(caloriesResult is DataResult.Success)
        val data = (caloriesResult as DataResult.Success).data
        assert(data.first().name == "Apple")
    }

    @Test
    fun `given CaloriesRepository, when searching for food calories, should return DataResultSuccess with empty list`() = runTest {
        fakeCalorieRepository.setResponseToBeSuccessButEmpty()
        val caloriesResult = fakeCalorieRepository.searchCalories("Apple")
        assert(caloriesResult is DataResult.Success)
        val data = (caloriesResult as DataResult.Success).data
        assert(data.isEmpty())
    }
}