package com.johndev.tmdb_guide.detailsActorModule.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.entities.Actor
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.common.entities.Pages
import com.johndev.tmdb_guide.common.utils.Constans.API_CAST
import com.johndev.tmdb_guide.common.utils.Constans.API_DISCOVER_MOVIE
import com.johndev.tmdb_guide.common.utils.Constans.API_DISCOVER_TV
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.API_PAGE
import com.johndev.tmdb_guide.common.utils.Constans.API_PERSON_DETAILS
import com.johndev.tmdb_guide.common.utils.Constans.API_SORT_POPULARITY_DESC
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import com.johndev.tmdb_guide.detailsActorModule.viewModel.DetailsActorViewModel.Companion.result
import com.johndev.tmdb_guide.detailsActorModule.viewModel.DetailsActorViewModel.Companion.resultMovies
import com.johndev.tmdb_guide.detailsActorModule.viewModel.DetailsActorViewModel.Companion.resultTV

class DetailsActorRemoteData(val context: Context) {

    fun consultActorById(id: String): Actor {
        var movie2 = Actor()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = URL_BASE + API_PERSON_DETAILS + id + API_KEY_INDEX_SEARCH + API_KEY
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val actor = gson.fromJson(response, Actor::class.java)
            //DetailsViewModel.result.value = movie
            result.value = actor
        }, { error ->

        })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return movie2
    }

    fun consultMoviesFromActorById(id: String): MutableList<Result> {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = URL_BASE + API_DISCOVER_MOVIE + API_KEY_INDEX_SEARCH + API_KEY + API_SORT_POPULARITY_DESC + API_PAGE + "1" + API_CAST + id
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val movies = gson.fromJson(response, Pages::class.java)
            //DetailsViewModel.result.value = movie
            resultMovies.value = movies.results as MutableList<Result>
        }, { error ->

        })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return mutableListOf()
    }

    fun consultTVFromActorById(id: String): MutableList<Result> {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = URL_BASE + API_DISCOVER_TV + API_KEY_INDEX_SEARCH + API_KEY + API_SORT_POPULARITY_DESC + API_PAGE + "1" + API_CAST + id
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val movies = gson.fromJson(response, Pages::class.java)
            //DetailsViewModel.result.value = movie
            resultTV.value = movies.results as MutableList<Result>
        }, { error ->

        })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return mutableListOf()
    }

}