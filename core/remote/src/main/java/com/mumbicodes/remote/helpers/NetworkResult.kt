package com.mumbicodes.remote.helpers

/**
 * A helper class to wrap all api responses
 *
 * [T] is the expected return type of the api request
 *
 * [Success] is returned when the if the request was successful
 *
 * [Error] is returned when the request fails
 */
sealed interface NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>
    data class Error(val message: String) : NetworkResult<Nothing>
}