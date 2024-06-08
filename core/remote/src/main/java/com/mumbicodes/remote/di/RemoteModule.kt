package com.mumbicodes.remote.di

import com.mumbicodes.remote.BuildConfig
import com.mumbicodes.remote.api.CaloriesSearchAPI
import com.mumbicodes.remote.api.CaloriesSearchApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    fun providesHttpClient() = HttpClient(OkHttp) {
        install(Logging) {
            level = LogLevel.BODY
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header("x-api-key", BuildConfig.key)
        }
    }

    @Provides
    fun providesCaloriesSearchApi(httpClient: HttpClient): CaloriesSearchAPI = CaloriesSearchApiImpl(httpClient)
}