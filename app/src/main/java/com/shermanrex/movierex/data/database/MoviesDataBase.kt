package com.shermanrex.movierex.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shermanrex.movierex.data.database.dao.MovieDao
import com.shermanrex.movierex.data.database.entitiy.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
)
abstract class MoviesDataBase: RoomDatabase() {
    abstract fun dao(): MovieDao
}