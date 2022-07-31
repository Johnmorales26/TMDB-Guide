package com.johndev.tmdb_guide.detailsMovieModel.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.tmdb_guide.common.entities.MoviesEntity
import com.johndev.tmdb_guide.detailsMovieModel.model.MovieRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MovieViewModel : ViewModel() {

    private val repository = MovieRepository()

    private val result = MutableLiveData<MoviesEntity>()
    fun getResult() = result

    fun consultMovieByID(id: Int) {
        viewModelScope.launch {
            result.value = repository.consultMovieByID(id)
        }
    }

    fun saveMovie(moviesEntity: MoviesEntity) {
        viewModelScope.launch {
            try {
                repository.saveMovie(moviesEntity)
                consultMovieByID(moviesEntity.id!!.toInt())
            } catch (e: Exception) {

            }
        }
    }

}