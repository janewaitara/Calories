package com.mumbicodes.data.domain.model

sealed interface DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>
    data class Error(val errorMessage: String, val exception: Exception) : DataResult<Nothing>
}