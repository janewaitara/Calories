package com.mumbicodes.calories.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.mumbicodes.calories.CaloriesScreenRoute
import com.mumbicodes.foodDetails.FoodDetailsScreenRoute
import com.mumbicodes.foodDetails.navigation.FOOD_DETAILS_ROUTE

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun Navigation() {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(bottomSheetNavigator)

    ModalBottomSheetLayout(bottomSheetNavigator) {
        NavHost(navController = navController, startDestination = CALORIES_ROUTE) {
            composable(route = CALORIES_ROUTE) {
                CaloriesScreenRoute(
                    onCalorieClicked = { navController.navigate(route = FOOD_DETAILS_ROUTE) }
                )
            }
            bottomSheet(route = FOOD_DETAILS_ROUTE) {
                FoodDetailsScreenRoute()
            }
        }
    }
}