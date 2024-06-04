package com.mumbicodes.remote.helpers

suspend fun <T> safeApiCall(
    errorMessage: String = "Failed to fetch calories",
    block: suspend () -> T
): NetworkResult<T> = try {
    val data = block.invoke()
    NetworkResult.Success(data)
} catch (e: Exception) {
    NetworkResult.Error(message = e.localizedMessage ?: errorMessage, exception = e)
}