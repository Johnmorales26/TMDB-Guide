package com.johndev.tmdb_guide

import com.johndev.tmdb_guide.common.entities.Videos

interface OnVideoListener {

    fun onClick(video: Videos)

}