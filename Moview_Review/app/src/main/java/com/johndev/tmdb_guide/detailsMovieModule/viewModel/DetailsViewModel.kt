package com.johndev.tmdb_guide.detailsMovieModule.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.tmdb_guide.common.entities.MovieDetailsEntity
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.detailsMovieModule.model.DetailsRepository
import com.johndev.tmdb_guide.detailsMovieModule.view.DetailsMovieActivity.Companion.contextDetails
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val repository = DetailsRepository(contextDetails)

    fun getResult() = result
    fun getSimilarMovies() = similarMovies

    fun consultMovieByID(id: String) {
        result.value = repository.consultMovieById(id)
    }

    fun consultSugerencesByID(id: String) {
        similarMovies.value = repository.consultSugerencesByID(id)
    }

    companion object {
        val result = MutableLiveData<MovieDetailsEntity>()
        val similarMovies = MutableLiveData<MutableList<Result>>()
    }

}