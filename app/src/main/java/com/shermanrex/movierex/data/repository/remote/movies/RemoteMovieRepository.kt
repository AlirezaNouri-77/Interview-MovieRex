package com.shermanrex.movierex.data.repository.remote.movies

import com.shermanrex.movierex.data.di.MovieRetrofit
import com.shermanrex.movierex.data.util.safeCall
import com.shermanrex.movierex.domain.model.MovieDetailModel
import com.shermanrex.movierex.domain.model.MovieModel
import com.shermanrex.movierex.domain.model.NetworkError
import com.shermanrex.movierex.domain.model.Result
import com.shermanrex.movierex.domain.repository.RemoteMovieRepositoryImpl
import javax.inject.Inject

class RemoteMovieRepository @Inject constructor(
    @MovieRetrofit private val retrofit: RetrofitApi,
) : RemoteMovieRepositoryImpl {

    override suspend fun getMovie(): Result<MovieModel, NetworkError> {
        return safeCall {
            retrofit.getMovies()
        }
    }

    override suspend fun getMovieDetail(movieID: String): Result<MovieDetailModel, NetworkError> {
        return safeCall {
            retrofit.getMovieDetail(movieID)
        }
    }

    override suspend fun getMovieByName(name: String): Result<MovieModel, NetworkError> {
        return safeCall {
            retrofit.getMovieByName(name)
        }
    }

}