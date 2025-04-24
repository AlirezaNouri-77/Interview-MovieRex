package com.shermanrex.movierex.repository

import com.shermanrex.movierex.data.database.entitiy.MovieEntity
import com.shermanrex.movierex.domain.repository.LocalMovieRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class LocalMovieRepositoryFake : LocalMovieRepositoryImpl {

    private var data = MutableStateFlow(emptyList<MovieEntity>())

    override suspend fun getAll(): List<MovieEntity> {
        return data.value
    }

    override suspend fun clearAll() {
        data.update { emptyList() }
    }

    override suspend fun insertData(list: List<MovieEntity>) {
        data.update { list }
    }

    override suspend fun clearAndInsert(list: List<MovieEntity>) {
        clearAll()
        insertData(list)
    }

}