package com.johndev.tmdb_guide.Provider.Services

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.common.adapters.CardMovieAdapter
import com.johndev.tmdb_guide.common.entities.DataRequestEntity
import com.johndev.tmdb_guide.common.entities.PagesPopularMovies
import com.johndev.tmdb_guide.common.utils.Constans.TYPE_MOVIE
import com.johndev.tmdb_guide.mainModel.viewModel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesService(
    val activity: FragmentActivity?,
    var context: Context?,
    var adapter: CardMovieAdapter?
) {



}