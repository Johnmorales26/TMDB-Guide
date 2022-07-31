package com.johndev.tmdb_guide

import android.view.View
import com.johndev.tmdb_guide.common.entities.Result

interface OnTVListener {

    fun onTVListener(movie: Result, imgPoster: View)

}