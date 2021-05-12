package com.example.kotlinmvvm_7.repository

import com.example.kotlinmvvm_7.model.Result
import com.example.kotlinmvvm_7.persistent.MovieDao

data class MovieRepository(val movieDao: MovieDao) {
    val movies = movieDao.getMovies()

    suspend fun insertMovie(movie: Result) = movieDao.insertMovie(movie)
    suspend fun deleteMovie(movie: Result) = movieDao.deleteMovie(movie)
    suspend fun uploadMovie(movie: Result) = movieDao.updateMovie(movie)
    suspend fun deleteAllMovie(movie: Result) = movieDao.insertMovie(movie)
}
