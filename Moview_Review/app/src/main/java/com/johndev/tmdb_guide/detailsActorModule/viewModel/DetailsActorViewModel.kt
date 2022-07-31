package com.johndev.tmdb_guide.detailsActorModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.johndev.tmdb_guide.common.entities.Actor
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.detailsActorModule.model.DetailsActorRepository
import com.johndev.tmdb_guide.detailsActorModule.view.DetailsActorActivity
import com.johndev.tmdb_guide.detailsActorModule.view.DetailsActorActivity.Companion.actorAcContext
import com.johndev.tmdb_guide.detailsMovieModule.view.DetailsMovieActivity
import com.johndev.tmdb_guide.detailsMovieModule.viewModel.DetailsViewModel

class DetailsActorViewModel : ViewModel() {

    private val repository = DetailsActorRepository(actorAcContext)

    fun getResult() = result
    fun getResultMovies() = resultMovies
    fun getResultTV() = resultTV

    fun consultActorByID(id: String) {
        result.value = repository.consultActorById(id)
    }

    fun consultMoviesFromActorById(id: String) {
        resultMovies.value = repository.consultMoviesFromActorById(id)
    }

    fun consultTVFromActorById(id: String) {
        resultTV.value = repository.consultTVFromActorById(id)
    }

    companion object {
        val result = MutableLiveData<Actor>()
        val resultMovies = MutableLiveData<MutableList<Result>>()
        val resultTV = MutableLiveData<MutableList<Result>>()
    }

}