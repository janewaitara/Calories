package com.mumbicodes.remote

import com.mumbicodes.remote.api.CaloriesSearchAPI
import com.mumbicodes.remote.api.CaloriesSearchApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private fun generateFakeEngine(statusCode: HttpStatusCode, networkResult: String) = MockEngine {
    val headers = headers {
        append(HttpHeaders.ContentType, "application/json")
    }
    respond(content = networkResult, status = statusCode, headers = headers)
}

private fun generateFakeClient(engine: HttpClientEngine) = HttpClient(engine) {
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
            }
        )
    }
    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }
}

fun generateFakeCaloriesSearchApiImpl(statusCode: HttpStatusCode, networkResult: String): CaloriesSearchAPI =
    CaloriesSearchApiImpl(
        httpClient = generateFakeClient(engine = generateFakeEngine(statusCode, networkResult))
    )