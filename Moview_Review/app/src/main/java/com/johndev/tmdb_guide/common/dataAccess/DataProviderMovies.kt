package com.johndev.tmdb_guide

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.utils.Constans.IMAGES_URL
import com.johndev.tmdb_guide.common.adapters.CardAdapter
import com.johndev.tmdb_guide.common.adapters.CardTVAdapter
import com.johndev.tmdb_guide.common.adapters.MoviesPremiresAdapter
import com.johndev.tmdb_guide.common.entities.Pages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun getImage(url: String): String = IMAGES_URL + url

suspend fun getPremires(context: Context, adapter: MoviesPremiresAdapter, url: String) = withContext(Dispatchers.IO) {
    // Instantiate the RequestQueue.
    val queue = Volley.newRequestQueue(context)
    val gson = Gson()
    // Request a string response from the provided URL.
    val stringRequest = StringRequest(Request.Method.GET, url, { response ->
        // Display the first 500 characters of the response string.
        val pages = gson.fromJson(response, Pages::class.java)
        pages.results?.forEach {
            println(it!!.title)
            adapter.add(it)
        }
            }, { error ->
            //textView.text = "That didn't work!"
        })
    // Add the request to the RequestQueue.
    queue.add(stringRequest)
}

suspend fun getPremires(context: Context, adapter: CardAdapter, url: String) = withContext(Dispatchers.IO) {
    // Instantiate the RequestQueue.
    val queue = Volley.newRequestQueue(context)
    val gson = Gson()
    // Request a string response from the provided URL.
    val stringRequest = StringRequest(Request.Method.GET, url, { response ->
        // Display the first 500 characters of the response string.
        val pages = gson.fromJson(response, Pages::class.java)
        pages.results?.forEach {
            println(it!!.title)
            adapter.add(it)
        }
            }, { error ->
            //textView.text = "That didn't work!"
        })
    // Add the request to the RequestQueue.
    queue.add(stringRequest)
}

suspend fun getPremires(context: Context, adapter: CardTVAdapter, url: String) = withContext(Dispatchers.IO) {
    // Instantiate the RequestQueue.
    val queue = Volley.newRequestQueue(context)
    val gson = Gson()
    // Request a string response from the provided URL.
    val stringRequest = StringRequest(Request.Method.GET, url, { response ->
        // Display the first 500 characters of the response string.
        val pages = gson.fromJson(response, Pages::class.java)
        pages.results?.forEach {
            println(it!!.title)
            adapter.add(it)
        }
            }, { error ->
            //textView.text = "That didn't work!"
        })
    // Add the request to the RequestQueue.
    queue.add(stringRequest)
}