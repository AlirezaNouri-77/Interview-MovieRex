package com.shermanrex.movierex.domain.usecase

import com.shermanrex.movierex.data.di.DispatcherIO
import com.shermanrex.movierex.domain.model.NetworkError
import com.shermanrex.movierex.domain.model.MovieDetailModel
import com.shermanrex.movierex.domain.model.Result
import com.shermanrex.movierex.domain.repository.RemoteMovieRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMovieDetailUseCase(
    private val remoteMovieRepository: RemoteMovieRepositoryImpl,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
) {
    operator fun invoke(movieID: String): Flow<Result<MovieDetailModel, NetworkError>> = flow {
        emit(remoteMovieRepository.getMovieDetail(movieID))
    }.flowOn(dispatcherIO)
}
