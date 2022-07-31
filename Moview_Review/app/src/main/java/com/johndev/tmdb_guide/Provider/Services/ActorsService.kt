package com.johndev.tmdb_guide.Provider.Services

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.adapters.ActorsAdapter
import com.johndev.tmdb_guide.common.entities.PageActors
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.API_LANGUAJE
import com.johndev.tmdb_guide.common.utils.Constans.API_PAGE
import com.johndev.tmdb_guide.common.utils.Constans.API_PERSON
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ActorsService(
    private val activity: FragmentActivity?,
    var context: Context?,
    var adapter: ActorsAdapter?
){

    suspend fun getDataActors(numPage: Int) = withContext(Dispatchers.IO) {
        val queue = Volley.newRequestQueue(activity)
        val gson = Gson()
        val url = URL_BASE + API_PERSON + API_KEY_INDEX_SEARCH + API_KEY + API_LANGUAJE + API_PAGE + numPage
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val testModel = gson.fromJson(response, PageActors::class.java)
            testModel.results?.forEach { actor ->
                if (actor != null) {
                    println("Actor --> $actor")
                    adapter?.add(actor)
                }
            }
        }) {
            Toast.makeText(context, "No such movies exists", Toast.LENGTH_LONG).show()
        }
        queue.add(stringRequest)
    }

}