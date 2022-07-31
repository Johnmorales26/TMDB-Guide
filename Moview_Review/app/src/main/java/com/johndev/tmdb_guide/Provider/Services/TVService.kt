package com.johndev.tmdb_guide.Provider.Services

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.API_LANGUAJE
import com.johndev.tmdb_guide.common.utils.Constans.API_PAGE
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import com.johndev.tmdb_guide.TV.CardTVAdapter
import com.johndev.tmdb_guide.TV.PagesTV
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TVService(val activity: FragmentActivity?, val context: Context, val adapter: CardTVAdapter) {

    suspend fun getDataTV(url: String, page: Int = 1) = withContext(Dispatchers.IO) {
        val queue = Volley.newRequestQueue(activity)
        val gson = Gson()
        val url = URL_BASE + url + API_KEY_INDEX_SEARCH + API_KEY + API_LANGUAJE + API_PAGE + page
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val testModel = gson.fromJson(response, PagesTV::class.java)
            println("$url --> $testModel")
            testModel.results?.forEach { tv ->
                if (tv != null) {
                    adapter.add(tv)
                }
            }
        }) {
            Toast.makeText(context, "No such movies exists", Toast.LENGTH_LONG).show()
        }
        queue.add(stringRequest)
    }

}