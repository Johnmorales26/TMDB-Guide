package com.johndev.tmdb_guide.detailsSerieModule.model

import android.content.Context

class DetailSeriesRepository(val context: Context) {

    private val remoteData = DetailsSerieRemoteData(context)

    fun consultSerieById(id: String) = remoteData.consultSerieByID(id)

    fun consultSugerencesByID(id: String) = remoteData.consultSugerencesByID(id)

    fun consultSeasonsById(id: String) = remoteData.consultSeasonsById(id)

}