package com.tiooooo.borutoapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tiooooo.borutoapp.utils.Constants

@Entity(tableName = Constants.HERO_REMOTE_KEYS_DATABASE_TABLE)
data class HeroRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)

