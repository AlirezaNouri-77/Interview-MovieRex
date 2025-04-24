package com.shermanrex.movierex.domain.usecase

import com.shermanrex.movierex.data.di.DispatcherIO
import com.shermanrex.movierex.data.util.toMovieData
import com.shermanrex.movierex.data.util.toMovieEntity
import com.shermanrex.movierex.domain.model.MovieModel
import com.shermanrex.movierex.domain.model.NetworkError
import com.shermanrex.movierex.domain.model.Result
import com.shermanrex.movierex.domain.repository.LocalMovieRepositoryImpl
import com.shermanrex.movierex.domain.repository.RemoteMovieRepositoryImpl
import com.shermanrex.movierex.domain.util.NetworkConnectivityImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val remoteMovieRepository: RemoteMovieRepositoryImpl,
    private val localMovieRepository: LocalMovieRepositoryImpl,
    private val networkConnectivity: NetworkConnectivityImpl,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
) {
    operator fun invoke(): Flow<Result<MovieModel, NetworkError>> = flow {
        var isNetWorkConnected = networkConnectivity.networkState.first()

        if (isNetWorkConnected) {
            val apiCallResponse = remoteMovieRepository.getMovie()

            if (apiCallResponse is Result.Success) {
                val movieData = apiCallResponse.data.movie.map { it.toMovieEntity() }
                localMovieRepository.clearAndInsert(movieData)
            }
            emit(apiCallResponse)
        } else {
            val dataBaseData = localMovieRepository.getAll()

            if (dataBaseData.isEmpty()) {
                emit(Result.Failure(NetworkError.INTERNET_CONNECTION))
            } else {
                emit(Result.Success(MovieModel(dataBaseData.map { it.toMovieData() }, null)))
            }
        }
    }.flowOn(dispatcherIO)
}