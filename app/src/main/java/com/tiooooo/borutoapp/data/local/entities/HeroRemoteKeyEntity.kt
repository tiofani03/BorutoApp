package com.tiooooo.borutoapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tiooooo.borutoapp.utils.Constants

@Entity(tableName = Constants.HERO_REMOTE_KEY_DATABASE_TABLE)
data class HeroRemoteKeyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)

