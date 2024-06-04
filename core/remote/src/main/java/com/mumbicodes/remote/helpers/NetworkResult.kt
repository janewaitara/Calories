package com.mumbicodes.remote.helpers

sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error(val message: String, val exception: Exception) : NetworkResult<Nothing>
}