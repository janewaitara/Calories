package com.mumbicodes.calories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mumbicodes.data.domain.model.Calorie
import com.mumbicodes.data.domain.model.DataResult
import com.mumbicodes.data.domain.repositories.CaloriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaloriesScreenViewModel @Inject constructor(
    private val caloriesRepository: CaloriesRepository
) : ViewModel() {

    private var _screenState: MutableStateFlow<CaloriesScreenState> = MutableStateFlow(CaloriesScreenState())
    val screenState = _screenState.asStateFlow()

    private var _calorieClicked: MutableStateFlow<Calorie> = MutableStateFlow(
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
    val calorieClicked
        get() = _calorieClicked.asStateFlow()

    init {
        getRecentSearches()
    }

    fun updateCalorie(calorie: Calorie) {
        _calorieClicked.update {
            calorie
        }
    }

    private fun getRecentSearches() {
        viewModelScope.launch {
            caloriesRepository.recentSearches.collectLatest { recentSearches ->
                _screenState.update {
                    it.copy(
                        recentSearches = recentSearches
                    )
                }
            }
        }
    }

    fun updateSearchParam(query: String) {
        _screenState.update {
            it.copy(
                searchParam = query
            )
        }
        if (query.isEmpty()) {
            _screenState.update {
                it.copy(
                    caloriesSearchResults = ListState.Idle
                )
            }
        }
    }

    fun searchCalories() {
        if (screenState.value.searchParam.isEmpty()) {
            return
        }

        _screenState.update {
            it.copy(
                caloriesSearchResults = ListState.Loading
            )
        }

        viewModelScope.launch {
            when (val results = caloriesRepository.searchCalories(screenState.value.searchParam)) {
                is DataResult.Error -> {
                    _screenState.update {
                        it.copy(
                            caloriesSearchResults = ListState.Error(errorMessage = results.errorMessage)
                        )
                    }
                }

                is DataResult.Success -> {
                    if (results.data.isEmpty()) {
                        _screenState.update {
                            it.copy(
                                caloriesSearchResults = ListState.Success(
                                    data = ListSuccessState.NoResults
                                )
                            )
                        }
                    } else {
                        _screenState.update {
                            it.copy(
                                caloriesSearchResults = ListState.Success(
                                    data = ListSuccessState.Data(results.data)
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}