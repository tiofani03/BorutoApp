package com.tiooooo.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tiooooo.borutoapp.data.local.dao.HeroDao
import com.tiooooo.borutoapp.data.local.dao.HeroRemoteKeyDao
import com.tiooooo.borutoapp.data.local.entities.HeroEntity
import com.tiooooo.borutoapp.data.local.entities.HeroRemoteKeyEntity

@Database(entities = [HeroEntity::class, HeroRemoteKeyEntity::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class BorutoDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}
