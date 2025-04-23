package com.shermanrex.movierex.data.repository.remote.movies

import com.shermanrex.movierex.data.model.MovieData
import com.shermanrex.movierex.data.model.NetWorkError
import com.shermanrex.movierex.data.model.MovieDetailModel
import com.shermanrex.movierex.data.model.MovieModel
import com.shermanrex.movierex.data.model.Result

interface MovieRepositoryImpl {
    suspend fun getMovie(): Result<MovieModel, NetWorkError>
    suspend fun getMovieDetail(movieID: String): Result<MovieDetailModel, NetWorkError>
    suspend fun getMovieByName(name: String): Result<MovieModel, NetWorkError>
}