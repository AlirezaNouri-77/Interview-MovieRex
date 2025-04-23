package com.shermanrex.movierex.domain

import com.shermanrex.movierex.data.di.DispatcherIO
import com.shermanrex.movierex.data.model.MovieData
import com.shermanrex.movierex.data.model.MovieModel
import com.shermanrex.movierex.data.model.NetWorkError
import com.shermanrex.movierex.data.model.Result
import com.shermanrex.movierex.data.repository.remote.movies.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMovieByNameUseCase(
    private val movieRepository: MovieRepository,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
) {
    operator fun invoke(name: String): Flow<Result<MovieModel, NetWorkError>> = flow {
        if (name.isNotBlank() or name.isNotEmpty()) {
            emit(movieRepository.getMovieByName(name))
        }
    }.flowOn(dispatcherIO)
}