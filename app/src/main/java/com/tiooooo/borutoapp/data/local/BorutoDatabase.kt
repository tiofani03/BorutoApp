package com.tiooooo.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tiooooo.borutoapp.data.local.dao.HeroDao
import com.tiooooo.borutoapp.data.local.dao.HeroRemoteKeysDao
import com.tiooooo.borutoapp.data.local.entities.HeroEntity
import com.tiooooo.borutoapp.data.local.entities.HeroRemoteKeysEntity

@Database(entities = [HeroEntity::class, HeroRemoteKeysEntity::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
}
