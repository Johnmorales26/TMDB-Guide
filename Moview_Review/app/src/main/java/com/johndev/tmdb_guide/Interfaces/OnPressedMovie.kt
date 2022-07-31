package com.johndev.tmdb_guide.Interfaces

import android.view.View
import com.johndev.tmdb_guide.common.entities.DataRequestEntity
import com.johndev.tmdb_guide.common.entities.KnownFor
import com.johndev.tmdb_guide.common.entities.MoviePopular

interface OnPressedMovie {

    fun OnMoviePressed(movie: DataRequestEntity, imgPhoto: View)
    fun OnMovieKnowPressed(movie: KnownFor)

}