package com.shermanrex.movierex.data.repository.local

import com.shermanrex.movierex.data.database.dao.MovieDao
import com.shermanrex.movierex.data.database.entitiy.MovieEntity
import com.shermanrex.movierex.domain.repository.LocalMovieRepositoryImpl
import javax.inject.Inject

class LocalMovieRepository @Inject constructor(
    private val movieDao: MovieDao,
) : LocalMovieRepositoryImpl {
    override suspend fun getAll(): List<MovieEntity> = movieDao.getAll()

    override suspend fun clearAll() = movieDao.clearDb()

    override suspend fun insertData(list: List<MovieEntity>) = movieDao.insertAll(list)

    override suspend fun clearAndInsert(list: List<MovieEntity>) = movieDao.clearAndInsert(list)
}

