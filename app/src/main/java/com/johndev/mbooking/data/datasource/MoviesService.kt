package com.johndev.mbooking.data.datasource

import com.johndev.mbooking.data.model.MovieResponse
import com.johndev.mbooking.data.model.MoviesResponse
import com.johndev.mbooking.data.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("${Constants.ENDPOINT_MOVIE}${Constants.ENDPOINT_UPCOMING}")
    suspend fun getMoviesUpcoming(
        @Query(Constants.API_KEY_PARAM) key: String,
        @Query(Constants.PARAM_LANGUAJE) lang: String,
        @Query(Constants.PARAM_PAGE) page: Int
    ): MoviesResponse

    @GET("${Constants.ENDPOINT_MOVIE}${Constants.ENDPOINT_NOW_PLAYING}")
    suspend fun getMoviesNowPlaying(
        @Query(Constants.API_KEY_PARAM) key: String,
        @Query(Constants.PARAM_LANGUAJE) lang: String,
        @Query(Constants.PARAM_PAGE) page: Int
    ): MoviesResponse

    @GET("${Constants.ENDPOINT_MOVIE}${Constants.ENDPOINT_POPULAR}")
    suspend fun getMoviesPopular(
        @Query(Constants.API_KEY_PARAM) key: String,
        @Query(Constants.PARAM_LANGUAJE) lang: String,
        @Query(Constants.PARAM_PAGE) page: Int
    ): MoviesResponse

    @GET("${Constants.ENDPOINT_MOVIE}${Constants.ENDPOINT_TOP_RATED}")
    suspend fun getMoviesTopRated(
        @Query(Constants.API_KEY_PARAM) key: String,
        @Query(Constants.PARAM_LANGUAJE) lang: String,
        @Query(Constants.PARAM_PAGE) page: Int
    ): MoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Long,
        @Query(Constants.API_KEY_PARAM) key: String,
        @Query(Constants.PARAM_LANGUAJE) lang: String
    ): MovieResponse

}