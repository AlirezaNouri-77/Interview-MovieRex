package com.shermanrex.movierex.data.di.useCase

import com.shermanrex.movierex.data.di.DispatcherIO
import com.shermanrex.movierex.data.repository.remote.movies.MovieRepository
import com.shermanrex.movierex.data.repository.remote.movies.MovieRepositoryImpl
import com.shermanrex.movierex.domain.GetMovieByNameUseCase
import com.shermanrex.movierex.domain.GetMovieDetailUseCase
import com.shermanrex.movierex.domain.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetMovieUseCase(
        movieRepositoryImpl: MovieRepository,
        @DispatcherIO dispatcher: CoroutineDispatcher,
    ): GetMoviesUseCase {
        return GetMoviesUseCase(
            movieRepository = movieRepositoryImpl,
            dispatcherIO = dispatcher,
        )
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailUseCase(
        movieRepositoryImpl: MovieRepository,
        @DispatcherIO dispatcher: CoroutineDispatcher,
    ): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(
            movieRepository = movieRepositoryImpl,
            dispatcherIO = dispatcher,
        )
    }

    @Provides
    @Singleton
    fun provideGetMovieByNameUseCase(
        movieRepositoryImpl: MovieRepository,
        @DispatcherIO dispatcher: CoroutineDispatcher,
    ): GetMovieByNameUseCase {
        return GetMovieByNameUseCase(
            movieRepository = movieRepositoryImpl,
            dispatcherIO = dispatcher,
        )
    }

}