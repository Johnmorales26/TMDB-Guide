package com.johndev.tmdb_guide.MainPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.johndev.tmdb_guide.PopularMovies.MoviePopular

class MainPageViewModel(val movies: List<MoviePopular>) : ViewModel()

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val movies: List<MoviePopular>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainPageViewModel(movies) as T
    }
}