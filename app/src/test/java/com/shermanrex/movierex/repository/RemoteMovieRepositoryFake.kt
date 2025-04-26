package com.shermanrex.movierex.repository

import com.shermanrex.movierex.domain.model.MovieDetailModel
import com.shermanrex.movierex.domain.model.MovieModel
import com.shermanrex.movierex.domain.model.NetworkError
import com.shermanrex.movierex.domain.model.Result
import com.shermanrex.movierex.domain.repository.RemoteMovieRepositoryImpl
import com.shermanrex.movierex.util.DummyData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

//class RemoteMovieRepositoryFake : RemoteMovieRepositoryImpl {
//
//    private var data: Result<MovieModel, NetworkError> =
//        Result.Success(MovieModel(movie = DummyData.movieDataDummyList))
//
//    override suspend fun getMovie(): Result<MovieModel, NetworkError> {
//        return data
//    }
//
//    override suspend fun getMovieDetail(movieID: String): Result<MovieDetailModel, NetworkError> {
//        return Result.Success(DummyData.movieDetailModel)
//    }
//
//    override suspend fun getMovieByName(name: String): Result<MovieModel, NetworkError> {
//        return data
//    }
//
//    fun setData(result: Result<MovieModel, NetworkError>) {
//        data = result
//    }
//
//}


class RemoteMovieRepositoryFake : RemoteMovieRepositoryImpl {

    private var movieData: MutableStateFlow<Result<MovieModel, NetworkError>> =
        MutableStateFlow(Result.Success(MovieModel(emptyList())))

    private var detailData: MutableStateFlow<Result<MovieDetailModel, NetworkError>> =
        MutableStateFlow(Result.Success(DummyData.movieDetailModel))

    override suspend fun getMovie(): Result<MovieModel, NetworkError> {
        return movieData.value
    }

    override suspend fun getMovieDetail(movieID: String): Result<MovieDetailModel, NetworkError> {
        return detailData.value
    }

    override suspend fun getMovieByName(name: String): Result<MovieModel, NetworkError> {
        if (movieData.value is Result.Failure) return movieData.value

        var data = movieData.value

        var result = if (data is Result.Success) {
            var resultList = data.data.movie.filter { it.title.contains(name) }
            Result.Success(MovieModel(resultList))
        } else Result.Success(MovieModel(emptyList()))

        return result
    }

    fun setMovieData(result: Result<MovieModel, NetworkError>) {
        movieData.update { result }
    }

    fun setDetailData(result: Result<MovieDetailModel, NetworkError>) {
        detailData.update { result }
    }

}