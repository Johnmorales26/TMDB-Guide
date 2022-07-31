package com.johndev.tmdb_guide.detailsMovieModule.model

import android.content.Context

class DetailsRepository(context: Context) {

    private val remoteData = DetailsRemoteData(context)

    fun consultMovieById(id: String) = remoteData.consultMovieByID(id)

    fun consultSugerencesByID(id: String) = remoteData.consultSugerencesByID(id)

}

