package com.johndev.mbooking.data.remote

import com.johndev.mbooking.data.datasource.MoviesService
import com.johndev.mbooking.data.model.MovieResponse
import com.johndev.mbooking.data.model.MoviesResponse
import retrofit2.Retrofit
import javax.inject.Inject

class MovieServiceImpl @Inject constructor(
    private val retrofit: Retrofit
) : MoviesService {

    override suspend fun getMoviesUpcoming(key: String, lang: String, page: Int): MoviesResponse {
        val service = retrofit.create(MoviesService::class.java)
        return service.getMoviesUpcoming(key = key, lang = lang, page = page)
    }

    override suspend fun getMoviesNowPlaying(key: String, lang: String, page: Int): MoviesResponse {
        val service = retrofit.create(MoviesService::class.java)
        return service.getMoviesNowPlaying(key = key, lang = lang, page = page)
    }

    override suspend fun getMoviesPopular(key: String, lang: String, page: Int): MoviesResponse {
        val service = retrofit.create(MoviesService::class.java)
        return service.getMoviesPopular(key = key, lang = lang, page = page)
    }

    override suspend fun getMoviesTopRated(key: String, lang: String, page: Int): MoviesResponse {
        val service = retrofit.create(MoviesService::class.java)
        return service.getMoviesTopRated(key = key, lang = lang, page = page)
    }

    override suspend fun getMovieDetails(movieId: Long, key: String, lang: String): MovieResponse {
        val service = retrofit.create(MoviesService::class.java)
        return service.getMovieDetails(movieId = movieId, key = key, lang = lang)
    }

}