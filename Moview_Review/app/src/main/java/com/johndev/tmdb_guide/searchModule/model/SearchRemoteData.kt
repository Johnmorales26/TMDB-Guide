package com.johndev.tmdb_guide.searchModule.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.entities.*
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.API_PAGE
import com.johndev.tmdb_guide.common.utils.Constans.API_QUERY_COMPANY
import com.johndev.tmdb_guide.common.utils.Constans.API_SEARCH_MOVIE
import com.johndev.tmdb_guide.common.utils.Constans.API_SEARCH_TV
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import com.johndev.tmdb_guide.mainModule.view.MainActivity.Companion.searchViewModel
import com.johndev.tmdb_guide.searchModule.viewModel.SearchViewModel
import com.johndev.tmdb_guide.videosModule.viewModel.VideosViewModel

class SearchRemoteData(val context: Context) {

    suspend fun getMoviesByQuery(query: String): MutableList<ResultSearch> {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = URL_BASE + API_SEARCH_MOVIE + API_KEY_INDEX_SEARCH + API_KEY + API_QUERY_COMPANY + query + API_PAGE + "1"
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val pages = gson.fromJson(response, PagesSearch::class.java)
            val videos: MutableList<ResultSearch> = mutableListOf()
            pages.results?.forEach {
                it.isMovie = true
                videos.add(it)
            }
            SearchViewModel.resultSearchMovie.value = videos
        }, { error ->

        })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return mutableListOf()
    }

    suspend fun getSeriesByQuery(query: String): MutableList<ResultSearch> {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = URL_BASE + API_SEARCH_TV + API_KEY_INDEX_SEARCH + API_KEY + API_QUERY_COMPANY + query + API_PAGE + "1"
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val pages = gson.fromJson(response, PagesSearch::class.java)
            val videos: MutableList<ResultSearch> = mutableListOf()
            pages.results?.forEach {
                it.isMovie = false
                videos.add(it)
            }
            SearchViewModel.resultSearchMovie.value = videos
        }, { error ->

        })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return mutableListOf()
    }

}