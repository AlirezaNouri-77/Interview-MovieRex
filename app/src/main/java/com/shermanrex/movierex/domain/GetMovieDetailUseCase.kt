package com.shermanrex.movierex.domain

import com.shermanrex.movierex.data.di.DispatcherIO
import com.shermanrex.movierex.data.model.NetWorkError
import com.shermanrex.movierex.data.model.MovieDetailModel
import com.shermanrex.movierex.data.model.Result
import com.shermanrex.movierex.data.repository.remote.movies.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMovieDetailUseCase(
    private val movieRepository: MovieRepository,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
) {
    operator fun invoke(movieID: String): Flow<Result<MovieDetailModel, NetWorkError>> = flow {
        emit(movieRepository.getMovieDetail(movieID))
    }.flowOn(dispatcherIO)
}
