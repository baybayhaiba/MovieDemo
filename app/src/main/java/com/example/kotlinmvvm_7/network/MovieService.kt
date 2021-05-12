package com.example.kotlinmvvm_7.network

import com.example.kotlinmvvm_7.model.Movie
import com.example.kotlinmvvm_7.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/movie/popular")
    suspend fun getMovie(
        @Query("api_key") api_key: String, @Query("language") language: String,
        @Query("page") page: Int
    ): Response<Movie>

    @GET("posts")
    suspend fun getTestPost(): Response<List<Post>>
}