package com.shermanrex.movierex.domain.usecase

import com.shermanrex.movierex.data.di.DispatcherIO
import com.shermanrex.movierex.domain.model.MovieModel
import com.shermanrex.movierex.domain.model.NetworkError
import com.shermanrex.movierex.domain.model.Result
import com.shermanrex.movierex.domain.repository.RemoteMovieRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMovieByNameUseCase(
    private val remoteMovieRepository: RemoteMovieRepositoryImpl,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
) {
    operator fun invoke(name: String): Flow<Result<MovieModel, NetworkError>> = flow {
        emit(remoteMovieRepository.getMovieByName(name))
    }.flowOn(dispatcherIO)
}