package com.johndev.tmdb_guide.detailsSerieModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.common.entities.Seasons
import com.johndev.tmdb_guide.common.entities.SeriesDetailsEntity
import com.johndev.tmdb_guide.detailsSerieModule.model.DetailSeriesRepository
import com.johndev.tmdb_guide.detailsSerieModule.view.DetailSeriesActivity.Companion.contextDetailsSeries

class DetailSeriesViewModel : ViewModel() {

    private val repository = DetailSeriesRepository(contextDetailsSeries)

    fun getResult() = result
    fun getSimilarMovies() = similarSeries
    fun getSeasons() = seasons

    fun consultSerieByID(id: String) {
        result.value = repository.consultSerieById(id)
    }

    fun consultSugerencesByID(id: String) {
        similarSeries.value = repository.consultSugerencesByID(id)
    }

    fun consultSeasonsById(id: String) {
        seasons.value = repository.consultSeasonsById(id)
    }

    companion object {
        val result = MutableLiveData<SeriesDetailsEntity>()
        val similarSeries = MutableLiveData<MutableList<Result>>()
        val seasons = MutableLiveData<MutableList<Seasons>>()
    }
}

