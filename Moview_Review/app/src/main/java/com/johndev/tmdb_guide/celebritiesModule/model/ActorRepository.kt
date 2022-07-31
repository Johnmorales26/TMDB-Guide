package com.johndev.tmdb_guide.celebritiesModule.model

import android.content.Context

class ActorRepository(val context: Context) {

    val remoteData = ActorRemoteData(context)

    suspend fun getMoviesByQuery(query: String) = remoteData.getMoviesByQuery(query)

}