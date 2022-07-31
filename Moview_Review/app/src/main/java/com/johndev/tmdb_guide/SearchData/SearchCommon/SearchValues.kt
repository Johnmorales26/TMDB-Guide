package com.johndev.tmdb_guide.SearchData.SearchCommon

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.API_PAGE
import com.johndev.tmdb_guide.common.utils.Constans.API_QUERY_COMPANY
import com.johndev.tmdb_guide.common.utils.Constans.API_SEARCH_MOVIE
import com.johndev.tmdb_guide.common.utils.Constans.API_SEARCH_PERSON
import com.johndev.tmdb_guide.common.utils.Constans.API_SEARCH_TV
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import com.johndev.tmdb_guide.TV.PagesTV
import com.johndev.tmdb_guide.common.adapters.SearchAdapter
import com.johndev.tmdb_guide.common.entities.*
import com.johndev.tmdb_guide.detailsActorModel.viewModel.ActorViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchValues(
    private val activity: FragmentActivity?,
    var context: Context?
) {

    private val gson = Gson()

    suspend fun getDataCompanies(url: String, num: Int = 1, adapter: SearchAdapter): Request<String> = withContext(
        Dispatchers.IO) {
        val queue = Volley.newRequestQueue(activity)
        val url = Constans.URL_BASE + Constans.API_SEARCH_COMPANY + Constans.API_KEY_INDEX_SEARCH + Constans.API_KEY + Constans.API_QUERY_COMPANY + url + Constans.API_PAGE + num
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val testModel = gson.fromJson(response, PageSearchCompany::class.java)
            testModel.results?.forEach { company ->
                company?.let {
                    val data = SearchData(it.id.toString().trim(), it.logo_path.toString().trim(),
                        it.name.toString().trim(), it.origin_country.toString().trim())
                    adapter.add(data)
                }
            }
        }) {
            Toast.makeText(context, "No such companies exists", Toast.LENGTH_LONG).show()
        }
        queue.add(stringRequest)
    }

    suspend fun getDataMovies(url: String, num: Int = 1, adapter: SearchAdapter): Request<String> = withContext(Dispatchers.IO) {
        val queue = Volley.newRequestQueue(activity)
        val url = URL_BASE + API_SEARCH_MOVIE + API_KEY_INDEX_SEARCH + API_KEY + API_QUERY_COMPANY + url + API_PAGE + num
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val testModel = gson.fromJson(response, PagesSearchMovies::class.java)
            testModel.results?.forEach { movie ->
                movie?.let {
                    println(movie)
                    val data = SearchData(it.id.toString().trim(), it.backdrop_path.toString().trim(),
                        it.title.toString().trim(), it.overview.toString().trim())
                    adapter.add(data)
                }
            }
        }) {
            Toast.makeText(context, "No such movies exists", Toast.LENGTH_LONG).show()
        }
        queue.add(stringRequest)
    }

    suspend fun getDataActor(url: String, num: Int = 1, adapter: SearchAdapter): Request<String> = withContext(
        Dispatchers.IO) {
        val queue = Volley.newRequestQueue(activity)
        val url = URL_BASE + API_SEARCH_PERSON + API_KEY_INDEX_SEARCH + API_KEY + API_QUERY_COMPANY + url + API_PAGE + num
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val testModel = gson.fromJson(response, PageActors::class.java)
            testModel.results?.forEach { actor ->
                actor?.let {
                    println(actor)
                    val data = SearchData(it.id.toString().trim(), it.profile_path.toString().trim(),
                        it.name.toString().trim(), it.biography.toString().trim())
                    adapter.add(data)
                }
            }
        }) {
            Toast.makeText(context, "No such actors exists", Toast.LENGTH_LONG).show()
        }
        queue.add(stringRequest)
    }

    suspend fun getDataTVShow(url: String, num: Int = 1, adapter: SearchAdapter): Request<String> = withContext(
        Dispatchers.IO) {
        val queue = Volley.newRequestQueue(activity)
        val url = URL_BASE + API_SEARCH_TV + API_KEY_INDEX_SEARCH + API_KEY + API_QUERY_COMPANY + url + API_PAGE + num
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val testModel = gson.fromJson(response, PagesTV::class.java)
            testModel.results?.forEach { actor ->
                actor?.let {
                    println(actor)
                    val data = SearchData(it.id.toString().trim(), it.backdrop_path.toString().trim(),
                        it.name.toString().trim(), it.overview.toString().trim())
                    adapter.add(data)
                }
            }
        }) {
            Toast.makeText(context, "No such actors exists", Toast.LENGTH_LONG).show()
        }
        queue.add(stringRequest)
    }

}