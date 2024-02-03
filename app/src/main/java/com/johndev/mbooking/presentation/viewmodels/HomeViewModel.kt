package com.johndev.mbooking.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.mbooking.data.model.Movies
import com.johndev.mbooking.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _moviesUpcoming = MutableLiveData<List<Movies>>()
    val moviesUpcoming: LiveData<List<Movies>> = _moviesUpcoming

    private val _moviesNowPlaying = MutableLiveData<List<Movies>>()
    val moviesNowPlaying: LiveData<List<Movies>> = _moviesNowPlaying

    private val _moviesPopular = MutableLiveData<List<Movies>>()
    val moviesPopular: LiveData<List<Movies>> = _moviesPopular

    private val _moviesTopRated = MutableLiveData<List<Movies>>()
    val moviesTopRated: LiveData<List<Movies>> = _moviesTopRated

    init {
        getMoviesUpcoming()
        getMoviesPopular()
        getMoviesNowPlaying()
        getMoviesTopRated()
    }

    private fun getMoviesUpcoming() {
        viewModelScope.launch {
            val response = repository.getMoviesUpcoming(lang = "es-MX", page = 1)
            _moviesUpcoming.value = response.results
        }
    }

    private fun getMoviesNowPlaying() {
        viewModelScope.launch {
            val response = repository.getMoviesNowPlaying(lang = "es-MX", page = 1)
            _moviesNowPlaying.value = response.results
        }
    }

    private fun getMoviesPopular() {
        viewModelScope.launch {
            val response = repository.getMoviesPopular(lang = "es-MX", page = 1)
            _moviesPopular.value = response.results
        }
    }

    private fun getMoviesTopRated() {
        viewModelScope.launch {
            val response = repository.getMoviesTopRated(lang = "es-MX", page = 1)
            _moviesTopRated.value = response.results
        }
    }

}