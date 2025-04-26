package com.shermanrex.movierex

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.memory.MemoryCache
import coil3.request.crossfade
import coil3.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieRexApplication : Application(), SingletonImageLoader.Factory {

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader(this).newBuilder().apply {
            memoryCache {
                MemoryCache.Builder().run {
                    maxSizePercent(this@MovieRexApplication, 0.05)
                    build()
                }
            }
            crossfade(true)
            logger(DebugLogger())
        }.build()
    }

}