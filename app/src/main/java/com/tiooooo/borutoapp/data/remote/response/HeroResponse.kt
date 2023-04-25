package com.tiooooo.borutoapp.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class HeroResponse(
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    val natureTypes: List<String>,
)
