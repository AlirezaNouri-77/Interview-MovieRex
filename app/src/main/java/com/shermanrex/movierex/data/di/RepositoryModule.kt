package com.shermanrex.movierex.data.di

import com.shermanrex.movierex.data.database.dao.MovieDao
import com.shermanrex.movierex.data.repository.local.LocalMovieRepository
import com.shermanrex.movierex.data.repository.remote.movies.RemoteMovieRepository
import com.shermanrex.movierex.data.repository.remote.movies.RetrofitApi
import com.shermanrex.movierex.domain.repository.LocalMovieRepositoryImpl
import com.shermanrex.movierex.domain.repository.RemoteMovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(retrofit: RetrofitApi): RemoteMovieRepositoryImpl {
        return RemoteMovieRepository(retrofit)
    }

    @Provides
    @Singleton
    fun provideDataBaseRepository(movieDao: MovieDao): LocalMovieRepositoryImpl {
        return LocalMovieRepository(movieDao)
    }

}