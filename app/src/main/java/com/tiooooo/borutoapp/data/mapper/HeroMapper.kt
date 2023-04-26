package com.tiooooo.borutoapp.data.mapper

import com.tiooooo.borutoapp.data.local.entities.HeroEntity
import com.tiooooo.borutoapp.data.remote.response.HeroResponse
import com.tiooooo.borutoapp.domain.model.Hero


fun HeroResponse.toHeroEntity() = HeroEntity(
    id = id,
    name = name,
    image = image,
    about = about,
    rating = rating,
    power = power,
    month = month,
    day = day,
    family = family,
    abilities = abilities,
    natureTypes = natureTypes
)

fun HeroEntity.toHero() = Hero(
    id = id,
    name = name,
    image = image,
    about = about,
    rating = rating,
    power = power,
    month = month,
    day = day,
    family = family,
    abilities = abilities,
    natureTypes = natureTypes
)
