package com.shermanrex.movierex.domain.repository

import com.shermanrex.movierex.domain.model.MovieDetailModel
import com.shermanrex.movierex.domain.model.MovieModel
import com.shermanrex.movierex.domain.model.NetworkError
import com.shermanrex.movierex.domain.model.Result

interface RemoteMovieRepositoryImpl {
    suspend fun getMovie(): Result<MovieModel, NetworkError>
    suspend fun getMovieDetail(movieID: String): Result<MovieDetailModel, NetworkError>
    suspend fun getMovieByName(name: String): Result<MovieModel, NetworkError>
}