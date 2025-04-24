package com.shermanrex.movierex.domain.repository

import com.shermanrex.movierex.data.database.entitiy.MovieEntity

interface LocalMovieRepositoryImpl {
    suspend fun getAll(): List<MovieEntity>
    suspend fun clearAll()
    suspend fun insertData(list: List<MovieEntity>)
    suspend fun clearAndInsert(list: List<MovieEntity>)
}