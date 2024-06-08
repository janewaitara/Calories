package com.mumbicodes.calories

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.mumbicodes.domain.model.Calorie
import org.junit.Rule
import org.junit.Test

class CaloriesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun onInitializationShouldShowHeading() {
        composeTestRule.setContent {
            CaloriesScreenContent(
                state = CaloriesScreenState(),
                onSearchParamChanged = {},
                onSearchClicked = { },
                onCalorieClicked = { _ ->
                }
            )
        }
        composeTestRule
            .onNodeWithTag("Calories heading")
            .assertIsDisplayed()
    }

    @Test
    fun onInitializationShouldShowSearchField() {
        composeTestRule.setContent {
            CaloriesScreenContent(
                state = CaloriesScreenState(),
                onSearchParamChanged = {},
                onSearchClicked = { },
                onCalorieClicked = { _ ->
                }
            )
        }
        composeTestRule
            .onNodeWithTag("Search field")
            .assertIsDisplayed()
    }

    @Test
    fun onInitializationShouldShowIdleStateSection() {
        composeTestRule.setContent {
            CaloriesScreenContent(
                state = CaloriesScreenState(),
                onSearchParamChanged = {},
                onSearchClicked = { },
                onCalorieClicked = { _ ->
                }
            )
        }
        composeTestRule
            .onNodeWithTag("Idle state")
            .assertIsDisplayed()
    }

    @Test
    fun whenLoadingShouldShowLoadingSection() {
        composeTestRule.setContent {
            CaloriesScreenContent(
                state = CaloriesScreenState(caloriesSearchResults = ListState.Loading),
                onSearchParamChanged = {},
                onSearchClicked = { },
                onCalorieClicked = { _ ->
                }
            )
        }
        composeTestRule
            .onNodeWithTag("Circular progress bar")
            .assertIsDisplayed()
    }

    @Test
    fun whenListStateIsErrorShouldShowErrorStateSection() {
        composeTestRule.setContent {
            CaloriesScreenContent(
                state = CaloriesScreenState(
                    caloriesSearchResults = ListState.Error(errorMessage = "A Error occurred")
                ),
                onSearchParamChanged = {},
                onSearchClicked = { },
                onCalorieClicked = { _ ->
                }
            )
        }
        composeTestRule
            .onNodeWithTag("Error state")
            .assertIsDisplayed()
    }

    @Test
    fun whenListStateIsSuccessWithNoDataShouldShowNoResultsSection() {
        composeTestRule.setContent {
            CaloriesScreenContent(
                state = CaloriesScreenState(
                    caloriesSearchResults = ListState.Success(data = ListSuccessState.NoResults)
                ),
                onSearchParamChanged = {},
                onSearchClicked = { },
                onCalorieClicked = { _ ->
                }
            )
        }
        composeTestRule
            .onNodeWithTag("No Results state")
            .assertIsDisplayed()
    }

    @Test
    fun whenListStateIsSuccessWithDataShouldShowResultsSection() {
        composeTestRule.setContent {
            CaloriesScreenContent(
                state = CaloriesScreenState(
                    caloriesSearchResults = ListState.Success(
                        data = ListSuccessState.Data(
                            data = listOf(
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
                    )
                ),
                onSearchParamChanged = {},
                onSearchClicked = { },
                onCalorieClicked = { _ ->
                }
            )
        }
        composeTestRule
            .onNodeWithTag("Results section")
            .assertIsDisplayed()
    }
}