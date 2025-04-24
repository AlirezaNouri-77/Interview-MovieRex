package com.shermanrex.movierex.data.di

import android.content.Context
import androidx.room.Room
import com.shermanrex.movierex.data.database.MoviesDataBase
import com.shermanrex.movierex.data.database.dao.MovieDao
import com.shermanrex.movierex.data.util.NetworkConnectivity
import com.shermanrex.movierex.domain.util.NetworkConnectivityImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): MoviesDataBase {
        return Room.databaseBuilder(
            context,
            MoviesDataBase::class.java,
            "movie_dataBase",
        ).build()
    }

    @Provides
    fun provideMovieDao(moviesDataBase: MoviesDataBase): MovieDao {
        return moviesDataBase.dao()
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivityImpl {
        return NetworkConnectivity(context)
    }

}