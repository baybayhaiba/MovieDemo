package com.example.kotlinmvvm_7.di

import android.app.Application
import androidx.room.Room
import com.example.kotlinmvvm_7.persistent.MovieDao
import com.example.kotlinmvvm_7.persistent.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistentModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(application, MovieDatabase::class.java, "Movie.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.movieDao
    }
}