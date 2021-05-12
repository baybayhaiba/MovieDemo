package com.example.kotlinmvvm_7.di

import android.app.Application
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.kotlinmvvm_7.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGlide(application: Application): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(
                RequestOptions().error(R.drawable.ic_baseline_error_24)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
            )
    }
}