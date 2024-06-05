package com.mumbicodes.calories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        getRecentSearches()
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
        if (screenState.value.searchParam.isNotEmpty()) {
            _screenState.update {
                it.copy(
                    caloriesSearchResults = ListState.Loading
                )
            }
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