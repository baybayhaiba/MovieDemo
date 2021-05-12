package com.example.kotlinmvvm_7.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinmvvm_7.model.Result
import com.example.kotlinmvvm_7.repository.MovieRepository
import com.example.kotlinmvvm_7.repository.RetrofitRepository
import com.example.kotlinmvvm_7.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val retrofitRepository: RetrofitRepository
) :
    ViewModel(), Observable {


    @get:Bindable
    val notification = MutableLiveData<String>()


    fun getMovies() = liveData(Dispatchers.IO) {
        emit(Resource.loading<Unit>())
        try {
            emit(Resource.success(data = retrofitRepository.getMovies().body()!!))
        } catch (e: Exception) {
            emit(Resource.error<String>(e.message!!))
        }
    }


    fun saveFavoriteMovie(result: Result) = viewModelScope.launch {
        if (movieRepository.insertMovie(result) > 0) {
            notification.value = "This movie add successes"
        } else {
            notification.value = "This movie exist or error"
        }
    }



    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}