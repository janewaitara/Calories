package com.mumbicodes.data.repositories

import com.mumbicodes.testing.FakeCaloriesRepository
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class CalorieRepositoryTest {

    private lateinit var fakeCaloriesRepository: FakeCaloriesRepository

    @Before
    fun setup() {
        fakeCaloriesRepository = FakeCaloriesRepository()
    }

    @After
    fun tearDown() {
        fakeCaloriesRepository.clearData()
    }

    @Test
    fun `given CaloriesRepository, when searching for food calories, should return DataResultError`() = runTest {
        fakeCaloriesRepository.setResponseToBeError()
        val caloriesResult = fakeCaloriesRepository.searchCalories("rice")
        assert(caloriesResult is com.mumbicodes.domain.model.DataResult.Error)
    }

    @Test
    fun `given CaloriesRepository, when searching for food calories, should return DataResultSuccess with Data`() = runTest {
        fakeCaloriesRepository.setResponseToBeSuccessWithData()
        val caloriesResult = fakeCaloriesRepository.searchCalories("Apple")
        assert(caloriesResult is com.mumbicodes.domain.model.DataResult.Success)
        val data = (caloriesResult as com.mumbicodes.domain.model.DataResult.Success).data
        assert(data.first().name == "Apple")
    }

    @Test
    fun `given CaloriesRepository, when searching for food calories, should return DataResultSuccess with empty list`() = runTest {
        fakeCaloriesRepository.setResponseToBeSuccessButEmpty()
        val caloriesResult = fakeCaloriesRepository.searchCalories("Apple")
        assert(caloriesResult is com.mumbicodes.domain.model.DataResult.Success)
        val data = (caloriesResult as com.mumbicodes.domain.model.DataResult.Success).data
        assert(data.isEmpty())
    }
}