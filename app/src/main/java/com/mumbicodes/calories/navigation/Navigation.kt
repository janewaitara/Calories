package com.mumbicodes.calories.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mumbicodes.calories.CaloriesScreenRoute

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = CALORIES_ROUTE) {
        composable(route = CALORIES_ROUTE) {
            CaloriesScreenRoute()
        }
    }
}