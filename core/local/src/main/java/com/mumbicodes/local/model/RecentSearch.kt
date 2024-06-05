package com.mumbicodes.local.model

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class RecentSearch(
    val searchParam: String,
    val timestamp: Long = Date().time
)