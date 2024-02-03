package com.johndev.mbooking.data.repository

import com.johndev.mbooking.data.datasource.MoviesService
import com.johndev.mbooking.data.model.MovieResponse
import com.johndev.mbooking.data.model.MoviesResponse
import com.johndev.mbooking.data.utils.Constants
import com.johndev.mbooking.data.utils.Constants.API_KEY_VALUE
import com.johndev.mbooking.di.RemoteData
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    @RemoteData private val service: MoviesService
) {

    suspend fun getMoviesUpcoming(lang: String, page: Int): MoviesResponse {
        return service.getMoviesUpcoming(key = Constants.API_KEY_VALUE, lang = lang, page = page)
    }

    suspend fun getMoviesNowPlaying(lang: String, page: Int): MoviesResponse {
        return service.getMoviesNowPlaying(key = Constants.API_KEY_VALUE, lang = lang, page = page)
    }

    suspend fun getMoviesPopular(lang: String, page: Int): MoviesResponse {
        return service.getMoviesPopular(key = Constants.API_KEY_VALUE, lang = lang, page = page)
    }

    suspend fun getMoviesTopRated(lang: String, page: Int): MoviesResponse {
        return service.getMoviesTopRated(key = Constants.API_KEY_VALUE, lang = lang, page = page)
    }

    suspend fun getMovieDetails(movieId: Long, lang: String): MovieResponse {
        return service.getMovieDetails(movieId = movieId, key = API_KEY_VALUE, lang = lang)
    }

}