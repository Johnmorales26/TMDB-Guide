package com.johndev.tmdb_guide.detailsMovieModule.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.entities.*
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.API_PERSON_MOVIE
import com.johndev.tmdb_guide.common.utils.Constans.API_SIMILAR
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import com.johndev.tmdb_guide.detailsMovieModule.viewModel.DetailsViewModel.Companion.result
import com.johndev.tmdb_guide.detailsMovieModule.viewModel.DetailsViewModel.Companion.similarMovies

class DetailsRemoteData(val context: Context) {

    fun consultMovieByID(id: String): MovieDetailsEntity {
        var movie2 = MovieDetailsEntity()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = URL_BASE + API_PERSON_MOVIE + id + API_KEY_INDEX_SEARCH + API_KEY
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val movie = gson.fromJson(response, MovieDetailsEntity::class.java)
            result.value = movie
        }, { error ->

        })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return movie2
    }

    fun consultSugerencesByID(id: String): MutableList<Result> {
        var movie2 = MovieDetailsEntity()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = URL_BASE + API_PERSON_MOVIE + id + API_SIMILAR + API_KEY_INDEX_SEARCH + API_KEY
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val pages = gson.fromJson(response, Pages::class.java)
            similarMovies.value = pages.results as MutableList<Result>
        }, { error ->

        })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return mutableListOf()
    }

}