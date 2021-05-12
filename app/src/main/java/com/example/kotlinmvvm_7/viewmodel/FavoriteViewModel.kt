package com.example.kotlinmvvm_7.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinmvvm_7.model.Result
import com.example.kotlinmvvm_7.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {
    val movies = repository.movies

    fun deleteMovieSaved(result: Result) = viewModelScope.launch {
        repository.deleteMovie(result)
    }
}