package com.johndev.tmdb_guide.videosModule.model

import android.content.Context

class VideosRepository(val context: Context) {

    private val remoteData = VideosRemoteData(context)

    fun getVideosById(id: String) = remoteData.getVideosById(id)

}