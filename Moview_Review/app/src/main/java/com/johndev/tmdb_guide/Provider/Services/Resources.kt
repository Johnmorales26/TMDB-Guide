package com.johndev.tmdb_guide.Provider.Services

import com.johndev.tmdb_guide.Constans.IMAGES_URL

class Resources {

    fun getImageResource(imgPath: String) = IMAGES_URL + imgPath

}