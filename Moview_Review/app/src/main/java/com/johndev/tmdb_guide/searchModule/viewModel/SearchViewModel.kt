package com.johndev.tmdb_guide.searchModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.tmdb_guide.common.entities.ResultSearch
import com.johndev.tmdb_guide.mainModule.view.MainActivity.Companion.searchContext
import com.johndev.tmdb_guide.searchModule.model.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val repository = SearchRepository(searchContext)

    fun getResult() = resultSearchMovie
    fun getResultSeries() = resultSearchSeries

    fun searchMoviesByQuery(query: String) {
        viewModelScope.launch {
            resultSearchMovie.value = repository.getVideosById(query)
        }
    }

    fun searchSeriesByQuery(query: String) {
        viewModelScope.launch {
            resultSearchMovie.value = repository.getSeriesByQuery(query)
        }
    }

    companion object {
        val resultSearchMovie = MutableLiveData<MutableList<ResultSearch>?>()
        val resultSearchSeries = MutableLiveData<MutableList<ResultSearch>?>()
    }

}