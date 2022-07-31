package com.johndev.tmdb_guide.detailsActorModule.model

import android.content.Context

class DetailsActorRepository(val context: Context) {

    private val remoteData = DetailsActorRemoteData(context)

    fun consultActorById(id: String) = remoteData.consultActorById(id)

    fun consultMoviesFromActorById(id: String) = remoteData.consultMoviesFromActorById(id)

    fun consultTVFromActorById(id: String) = remoteData.consultTVFromActorById(id)

}