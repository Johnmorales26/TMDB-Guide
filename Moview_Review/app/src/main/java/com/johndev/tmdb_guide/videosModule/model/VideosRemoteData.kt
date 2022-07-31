package com.johndev.tmdb_guide.videosModule.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.entities.MovieDetailsEntity
import com.johndev.tmdb_guide.common.entities.PagesVideos
import com.johndev.tmdb_guide.common.entities.Videos
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.videosModule.viewModel.VideosViewModel

class VideosRemoteData(val context: Context) {

    fun getVideosById(id: String): MutableList<Videos> {
        var movie2 = MovieDetailsEntity()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = Constans.URL_BASE + Constans.API_PERSON_MOVIE + id + Constans.API_VIDEOS + Constans.API_KEY_INDEX_SEARCH + Constans.API_KEY
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val pages = gson.fromJson(response, PagesVideos::class.java)
            val videos: MutableList<Videos> = mutableListOf()
            pages.results?.forEach {
                videos.add(it)
            }
            //DetailsViewModel.result.value = movi
            VideosViewModel.result.value = videos
        }, { error ->

        })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return mutableListOf()
    }

}