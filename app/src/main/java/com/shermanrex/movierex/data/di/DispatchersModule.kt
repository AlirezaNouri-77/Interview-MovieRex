package com.shermanrex.movierex.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

  @Provides
  @DispatcherIO
  fun provideDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

  @Provides
  @DispatcherDefault
  fun provideDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

}