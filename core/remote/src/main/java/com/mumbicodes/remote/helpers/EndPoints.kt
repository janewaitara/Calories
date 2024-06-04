package com.mumbicodes.remote.helpers

sealed class EndPoints(private val path: String) {

    val url = buildString {
        append("https://api.calorieninjas.com")
        append(path)
    }

    data object Nutrition : EndPoints(path = "/v1/nutrition")
}