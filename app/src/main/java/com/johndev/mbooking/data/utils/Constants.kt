package com.johndev.mbooking.data.utils
import org.dotenv.vault.dotenvVault

object Constants {

    val dotenv = dotenvVault()
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY_PARAM = "&api_key="
    const val API_KEY_PARAM_SEARCH = "?api_key="
    const val SESSION_ID_PARAM = "&session_id="

    val API_KEY_VALUE = dotenv["API_KEY"]

    // Tipos de consultas
    const val ENDPOINT_PERSON = "person/popular"
    const val ENDPOINT_TV_AIRING_TODAY = "tv/airing_today"
    const val ENDPOINT_TV_LATEST = "tv/latest"
    const val ENDPOINT_TV_ON_THE_AIR = "tv/on_the_air"
    const val ENDPOINT_TV_POPULAR = "tv/popular"
    const val ENDPOINT_MOVIE_POPULAR = "movie/popular"
    const val ENDPOINT_MOVIE_NOW_PLAYING = "movie/now_playing"
    const val ENDPOINT_MOVIE_TOP_RATED = "movie/top_rated"
    const val ENDPOINT_DISCOVER_TV = "discover/tv"
    const val ENDPOINT_DISCOVER_MOVIE = "discover/movie"

    // Parámetros comunes
    const val SORT_BY_POPULARITY_DESC = "&sort_by=popularity.desc"
    const val CAST_PARAM = "&with_cast="

    // Otros tipos de consultas
    const val ENDPOINT_MOVIE_UPCOMING = "movie/upcoming"
    const val ENDPOINT_TV_TOP_RATED = "tv/top_rated"
    const val ENDPOINT_COMPANY_DETAILS = "company/"
    const val ENDPOINT_PERSON_DETAILS = "person/"
    const val ENDPOINT_PERSON_MOVIE = "movie/"
    const val SIMILAR_SUFFIX = "/similar"
    const val VIDEOS_SUFFIX = "/videos"
    const val SERIES_PREFIX = "tv/"
    const val ENDPOINT_ACCOUNT = "account"
    const val QUERY_COMPANY_PARAM = "&query="
    const val ENDPOINT_AUTHENTICATION = "authentication/"
    const val ENDPOINT_SESSION = "session/new"
    const val ENDPOINT_TOKEN = "token/new"
    const val ENDPOINT_VALIDATE_LOGIN = "token/validate_with_login"
    const val USE_PERMISSION_URL = "https://www.themoviedb.org/authenticate/"

    // Parámetros adicionales
    const val LANGUAGE_PARAM = "&language=en-US"
    const val PAGE_PARAM = "&page="

    // Búsquedas
    const val ENDPOINT_SEARCH_COMPANY = "search/company"
    const val ENDPOINT_SEARCH_MOVIE = "search/movie"
    const val ENDPOINT_SEARCH_PERSON = "search/person"
    const val ENDPOINT_SEARCH_TV = "search/tv"

    // Consultas predefinidas
    const val ENDPOINT_POPULAR_MOVIES = "discover/movie?sort_by=popularity.desc"
    const val ENDPOINT_CURRENT_MOVIES =
        "discover/movie?primary_release_date.gte=2014-09-15&primary_release_date.lte=2021-07-04"
    const val ENDPOINT_YEAR_DRAMA = "discover/movie?with_genres=18&primary_release_year=2014"
    const val ENDPOINT_TOM_CRUISE_MOVIES =
        "discover/movie?with_genres=878&with_cast=500&sort_by=vote_average.desc"

    const val IMAGES_URL = "https://image.tmdb.org/t/p/w500/"

}
