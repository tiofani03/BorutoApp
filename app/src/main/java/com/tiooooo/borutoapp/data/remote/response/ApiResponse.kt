package com.tiooooo.borutoapp.data.remote.response

import com.tiooooo.borutoapp.domain.model.Hero
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    var heroes: List<Hero> = emptyList(),
    val lastUpdated: Long? = null,
)
