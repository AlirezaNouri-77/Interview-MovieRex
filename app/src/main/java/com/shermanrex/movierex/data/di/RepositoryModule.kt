package com.shermanrex.movierex.data.di

import com.shermanrex.movierex.data.repository.remote.movies.RetrofitApi
import com.shermanrex.movierex.data.repository.remote.movies.MovieRepository
import com.shermanrex.movierex.data.repository.remote.movies.MovieRepositoryImpl
import dagger.Binds
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
    fun provideMovieRepository(retrofit: RetrofitApi): MovieRepository {
        return MovieRepository(retrofit)
    }

}