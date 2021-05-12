package com.example.kotlinmvvm_7.persistent

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlinmvvm_7.model.Result

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(result: Result): Long

    @Delete
    suspend fun deleteMovie(result: Result): Int

    @Update
    suspend fun updateMovie(result: Result): Int

    @Query("DELETE FROM moviesTable")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM moviesTable ORDER BY movieSaved DESC")
    fun getMovies(): LiveData<List<Result>>
}