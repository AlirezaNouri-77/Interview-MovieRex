package com.shermanrex.movierex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.shermanrex.movierex.data.database.entitiy.MovieEntity

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(movieEntity: List<MovieEntity>)

    @Query("SELECT * FROM movieentity")
    suspend fun getAll(): List<MovieEntity>

    @Query("DELETE FROM movieentity")
    suspend fun clearDb()

    @Transaction
    suspend fun clearAndInsert(movieEntity: List<MovieEntity>) {
        clearDb()
        insertAll(movieEntity)
    }
}
