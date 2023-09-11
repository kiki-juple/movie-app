package com.kiki.core.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kiki.core.data.source.local.entity.MovieDetailEntity
import com.kiki.core.data.source.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class, MovieDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}