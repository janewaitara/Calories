package com.mumbicodes.calories

import com.mumbicodes.data.domain.model.Calorie

data class CaloriesScreenState(
    val searchParam: String = "",
    val caloriesSearchResults: ListState<List<Calorie>> = ListState.Idle
)

sealed interface ListState<out T> {
    data object Idle : ListState<Nothing>
    data object Loading : ListState<Nothing>
    data class Error(val errorMessage: String) : ListState<Nothing>
    data class Success<T>(val data: ListSuccessState<T>) : ListState<T>
}

sealed interface ListSuccessState<out T> {
    data object NoResults : ListSuccessState<Nothing>
    data class Data<T>(val data: T) : ListSuccessState<T>
}