package com.shermanrex.movierex.data.di.useCase

import com.shermanrex.movierex.data.di.DispatcherIO
import com.shermanrex.movierex.domain.repository.LocalMovieRepositoryImpl
import com.shermanrex.movierex.domain.repository.RemoteMovieRepositoryImpl
import com.shermanrex.movierex.domain.usecase.GetMovieByNameUseCase
import com.shermanrex.movierex.domain.usecase.GetMovieDetailUseCase
import com.shermanrex.movierex.domain.usecase.GetMoviesUseCase
import com.shermanrex.movierex.domain.util.NetworkConnectivityImpl
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
        movieRepositoryImpl: RemoteMovieRepositoryImpl,
        dataBaseRepository: LocalMovieRepositoryImpl,
        networkConnectivity: NetworkConnectivityImpl,
        @DispatcherIO dispatcher: CoroutineDispatcher,
    ): GetMoviesUseCase {
        return GetMoviesUseCase(
            remoteMovieRepository = movieRepositoryImpl,
            localMovieRepository = dataBaseRepository,
            networkConnectivity = networkConnectivity,
            dispatcherIO = dispatcher,
        )
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailUseCase(
        movieRepositoryImpl: RemoteMovieRepositoryImpl,
        @DispatcherIO dispatcher: CoroutineDispatcher,
    ): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(
            remoteMovieRepository = movieRepositoryImpl,
            dispatcherIO = dispatcher,
        )
    }

    @Provides
    @Singleton
    fun provideGetMovieByNameUseCase(
        movieRepositoryImpl: RemoteMovieRepositoryImpl,
        @DispatcherIO dispatcher: CoroutineDispatcher,
    ): GetMovieByNameUseCase {
        return GetMovieByNameUseCase(
            remoteMovieRepository = movieRepositoryImpl,
            dispatcherIO = dispatcher,
        )
    }

}