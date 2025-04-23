package com.shermanrex.movierex

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.request.crossfade
import coil3.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers

@HiltAndroidApp
class MovieRexApplication : Application(), SingletonImageLoader.Factory {

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader(this).newBuilder().apply {
            diskCache {
                DiskCache
                    .Builder().run {
                        maxSizePercent(0.1)
                        directory(this@MovieRexApplication.cacheDir.resolve("image_cache"))
                        cleanupCoroutineContext(Dispatchers.IO)
                        build()
                    }
            }
            crossfade(true)
            logger(DebugLogger())
        }.build()
    }

}