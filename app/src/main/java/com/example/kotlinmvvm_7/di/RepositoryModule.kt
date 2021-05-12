package com.example.kotlinmvvm_7.di

import com.example.kotlinmvvm_7.network.MovieService
import com.example.kotlinmvvm_7.persistent.MovieDao
import com.example.kotlinmvvm_7.repository.MovieRepository
import com.example.kotlinmvvm_7.repository.RetrofitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideRetrofitRepository(movieService: MovieService): RetrofitRepository {
        return RetrofitRepository(movieService)
    }

    @Provides
    @ViewModelScoped
    fun provideMovieRepository(movieDao: MovieDao): MovieRepository {
        return MovieRepository(movieDao)
    }
}