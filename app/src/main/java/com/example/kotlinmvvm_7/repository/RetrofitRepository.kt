package com.example.kotlinmvvm_7.repository

import com.example.kotlinmvvm_7.network.MovieService

import com.example.kotlinmvvm_7.utils.Const.API_KEY
import com.example.kotlinmvvm_7.utils.Const.LANGUAGE
import com.example.kotlinmvvm_7.utils.Const.PAGE

class RetrofitRepository(private val movieService: MovieService) {
    suspend fun getMovies() = movieService.getMovie(API_KEY, LANGUAGE, PAGE)
}