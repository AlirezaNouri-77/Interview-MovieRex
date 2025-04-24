package com.shermanrex.movierex.domain.usecase

import com.shermanrex.movierex.data.database.entitiy.MovieEntity
import com.shermanrex.movierex.data.di.DispatcherIO
import com.shermanrex.movierex.domain.repository.LocalMovieRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMoviesDataBase @Inject constructor(
    private val localMovieRepository: LocalMovieRepositoryImpl,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
) {
    suspend operator fun invoke(): List<MovieEntity> {
        return withContext(dispatcherIO) {
            localMovieRepository.getAll()
        }
    }
}