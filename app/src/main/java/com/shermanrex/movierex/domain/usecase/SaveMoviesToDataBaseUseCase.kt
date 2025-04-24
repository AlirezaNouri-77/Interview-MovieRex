package com.shermanrex.movierex.domain.usecase

import com.shermanrex.movierex.data.di.DispatcherIO
import com.shermanrex.movierex.data.util.toMovieEntity
import com.shermanrex.movierex.domain.model.MovieData
import com.shermanrex.movierex.domain.repository.LocalMovieRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveMoviesToDataBaseUseCase @Inject constructor(
    private val localMovieRepository: LocalMovieRepositoryImpl,
    @DispatcherIO private val dispatcherIO: CoroutineDispatcher,
) {
    suspend operator fun invoke(list: List<MovieData>) {
        withContext(dispatcherIO) {
            val movieListEntity = list.map { it.toMovieEntity() }
            localMovieRepository.clearAndInsert(movieListEntity)
        }
    }
}