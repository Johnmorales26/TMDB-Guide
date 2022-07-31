package com.johndev.tmdb_guide.searchModule.model

import android.content.Context

class SearchRepository(val context: Context) {

    val remoteData = SearchRemoteData(context)

    suspend fun getVideosById(query: String) = remoteData.getMoviesByQuery(query)

    suspend fun getSeriesByQuery(query: String) = remoteData.getSeriesByQuery(query)

}