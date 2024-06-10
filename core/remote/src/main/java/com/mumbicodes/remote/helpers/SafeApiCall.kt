package com.mumbicodes.remote.helpers

/**
 * A helper class to catch any exception during the api request
 *
 * [errorMessage] is the default message in case an error occurs and no message is returned
 *
 * [block] the function being executed to return the data
 */
suspend fun <T> safeApiCall(
    errorMessage: String = "Failed to fetch calories",
    block: suspend () -> T
): NetworkResult<T> = try {
    val data = block.invoke()
    NetworkResult.Success(data)
} catch (e: Exception) {
    NetworkResult.Error(message = e.localizedMessage ?: errorMessage)
}