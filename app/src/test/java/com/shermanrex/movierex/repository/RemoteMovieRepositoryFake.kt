package com.shermanrex.movierex.repository

import com.shermanrex.movierex.domain.model.MovieDetailModel
import com.shermanrex.movierex.domain.model.MovieModel
import com.shermanrex.movierex.domain.model.NetworkError
import com.shermanrex.movierex.domain.model.Result
import com.shermanrex.movierex.domain.repository.RemoteMovieRepositoryImpl
import com.shermanrex.movierex.util.movieDataDummyList
import com.shermanrex.movierex.util.movieDetailModel

class RemoteMovieRepositoryFake : RemoteMovieRepositoryImpl {

    private var data: Result<MovieModel, NetworkError> =
        Result.Success(MovieModel(movie = movieDataDummyList, null))

    override suspend fun getMovie(): Result<MovieModel, NetworkError> {
        return data
    }

    override suspend fun getMovieDetail(movieID: String): Result<MovieDetailModel, NetworkError> {
        return Result.Success(movieDetailModel)
    }

    override suspend fun getMovieByName(name: String): Result<MovieModel, NetworkError> {
        return data
    }

    fun setData(result: Result<MovieModel, NetworkError>) {
        data = result
    }

}