package com.johndev.tmdb_guide.celebritiesModule.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.entities.PagesActor
import com.johndev.tmdb_guide.common.entities.ResultPerson
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.API_PAGE
import com.johndev.tmdb_guide.common.utils.Constans.API_QUERY_COMPANY
import com.johndev.tmdb_guide.common.utils.Constans.API_SEARCH_PERSON
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import com.johndev.tmdb_guide.celebritiesModule.viewModel.ActorViewModel.Companion.actorResult

class ActorRemoteData(val context: Context) {

    suspend fun getMoviesByQuery(query: String): MutableList<ResultPerson> {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = URL_BASE + API_SEARCH_PERSON + API_KEY_INDEX_SEARCH + API_KEY + API_QUERY_COMPANY + query + API_PAGE + 1
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val actors = gson.fromJson(response, PagesActor::class.java)
            val videos: MutableList<ResultPerson> = mutableListOf()
            actors.results?.forEach {
                val actor = ResultPerson(it.id!!.toLong(), it.name.toString().trim(), it.profile_path.toString().trim(), it.known_for_department.toString().trim())
                videos.add(actor)
            }
            actorResult.value = videos
        }, { error ->

        })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return mutableListOf()
    }

}