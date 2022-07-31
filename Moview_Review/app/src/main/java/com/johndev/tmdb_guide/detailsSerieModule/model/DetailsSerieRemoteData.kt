package com.johndev.tmdb_guide.detailsSerieModule.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.entities.MovieDetailsEntity
import com.johndev.tmdb_guide.common.entities.Pages
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.common.entities.Seasons
import com.johndev.tmdb_guide.common.entities.SeriesDetailsEntity
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.API_SERIES
import com.johndev.tmdb_guide.common.utils.Constans.API_SIMILAR
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import com.johndev.tmdb_guide.detailsMovieModule.viewModel.DetailsViewModel
import com.johndev.tmdb_guide.detailsSerieModule.viewModel.DetailSeriesViewModel.Companion.result
import com.johndev.tmdb_guide.detailsSerieModule.viewModel.DetailSeriesViewModel.Companion.seasons
import com.johndev.tmdb_guide.detailsSerieModule.viewModel.DetailSeriesViewModel.Companion.similarSeries

class DetailsSerieRemoteData(val context: Context) {

    fun consultSerieByID(id: String): SeriesDetailsEntity {
        val serie = SeriesDetailsEntity()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = Constans.URL_BASE + Constans.API_SERIES + id + Constans.API_KEY_INDEX_SEARCH + Constans.API_KEY
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val series = gson.fromJson(response, SeriesDetailsEntity::class.java)
            result.value = series
        }, { error ->
            result.value = null
        })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return serie
    }

    fun consultSugerencesByID(id: String): MutableList<Result> {
        var movie2 = MovieDetailsEntity()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = URL_BASE + API_SERIES + id + API_SIMILAR + API_KEY_INDEX_SEARCH + API_KEY
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val pages = gson.fromJson(response, Pages::class.java)
            similarSeries.value = pages.results as MutableList<Result>
        }, { error ->
            similarSeries.value = null
        })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return mutableListOf()
    }

    fun consultSeasonsById(id: String): MutableList<Seasons> {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = Constans.URL_BASE + Constans.API_SERIES + id + Constans.API_KEY_INDEX_SEARCH + Constans.API_KEY
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val series = gson.fromJson(response, SeriesDetailsEntity::class.java)
            seasons.value = series.seasons as MutableList<Seasons>
        }, { error ->
            seasons.value = null
        })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return mutableListOf()
    }

}