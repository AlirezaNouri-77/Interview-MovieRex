package com.shermanrex.movierex.data.repository.remote.movies

import com.shermanrex.movierex.data.di.MovieRetrofit
import com.shermanrex.movierex.data.model.MovieData
import com.shermanrex.movierex.data.model.NetWorkError
import com.shermanrex.movierex.data.model.MovieDetailModel
import com.shermanrex.movierex.data.model.MovieModel
import com.shermanrex.movierex.data.model.Result
import com.shermanrex.movierex.data.util.safeCall
import javax.inject.Inject

class MovieRepository @Inject constructor(
    @MovieRetrofit private val retrofit: RetrofitApi,
) : MovieRepositoryImpl {

    override suspend fun getMovie(): Result<MovieModel, NetWorkError> {
        return safeCall {
            retrofit.getMovies()
        }
    }

    override suspend fun getMovieDetail(movieID: String): Result<MovieDetailModel, NetWorkError> {
        return safeCall {
            retrofit.getMovieDetail(movieID)
        }
    }

    override suspend fun getMovieByName(name: String): Result<MovieModel, NetWorkError> {
        return safeCall {
            retrofit.getMovieByName(name)
        }
    }

}