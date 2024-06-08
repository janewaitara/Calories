package com.mumbicodes.calories

import com.mumbicodes.testing.FakeCaloriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CaloriesScreenViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var fakeCaloriesRepository: FakeCaloriesRepository
    private lateinit var viewModel: CaloriesScreenViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        fakeCaloriesRepository = FakeCaloriesRepository()
        viewModel = CaloriesScreenViewModel(fakeCaloriesRepository)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    fun `given CaloriesScreenViewModel, on updating search param, search param state is updated`() =
        runTest {
            viewModel.updateSearchParam("rice")
            assertEquals(viewModel.screenState.value.searchParam, "rice")
        }

    @Test
    fun `given CaloriesScreenViewModel, on initialisation, the recent searches should be empty`() =
        runTest {
            val recentSearches = viewModel.screenState.value.recentSearches
            assertEquals(listOf<String>(), recentSearches)
        }

    @Test
    fun `given CaloriesScreenViewModel, on updating the recent searches, the list has data`() =
        runTest {
            val recentSearchesJob: Job = launch {
                fakeCaloriesRepository.updateRecentSearchesState(listOf("Apple", "Managu"))
                viewModel.getRecentSearches()
            }
            recentSearchesJob.join()

            val recentSearches = viewModel.screenState.value.recentSearches
            assertEquals(listOf("Apple", "Managu"), recentSearches)
        }

    @Test
    fun `given CaloriesScreenViewModel, on searching calories, the search list should be ListStateError`() =
        runTest {
            val recentSearchesJob: Job = launch {
                fakeCaloriesRepository.setResponseToBeError()
                viewModel.updateSearchParam("rice")
                viewModel.searchCalories()
            }
            recentSearchesJob.join()

            val searchResults = viewModel.screenState.value.caloriesSearchResults
            assert(searchResults is ListState.Error)
        }

    @Test
    fun `given CaloriesScreenViewModel, on searching calories, the search list should be ListStateSuccess but empty`() =
        runTest {
            val recentSearchesJob: Job = launch {
                fakeCaloriesRepository.setResponseToBeSuccessButEmpty()
                viewModel.updateSearchParam("rice")
                viewModel.searchCalories()
            }
            recentSearchesJob.join()

            val searchResults = viewModel.screenState.value.caloriesSearchResults
            assert(searchResults is ListState.Success)
            val data = (searchResults as ListState.Success).data
            assert(data is ListSuccessState.NoResults)
        }

    @Test
    fun `given CaloriesScreenViewModel, on searching calories, the search list should be ListStateSuccess with data`() =
        runTest {
            val recentSearchesJob: Job = launch {
                fakeCaloriesRepository.setResponseToBeSuccessWithData()
                viewModel.updateSearchParam("rice")
                viewModel.searchCalories()
            }
            recentSearchesJob.join()

            val searchResults = viewModel.screenState.value.caloriesSearchResults
            assert(searchResults is ListState.Success)
            val data = ((searchResults as ListState.Success).data as ListSuccessState.Data).data
            assert(data.first().name == "Apple")
        }
}