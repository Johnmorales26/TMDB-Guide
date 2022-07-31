package com.johndev.tmdb_guide

import android.view.View
import com.johndev.tmdb_guide.common.entities.Result

interface OnMovieListener {

    fun onMovieListener(movie: Result, imgPoster: View)

}