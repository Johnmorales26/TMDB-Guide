package com.johndev.tmdb_guide

object Constans {

    const val URL_BASE = "https://api.themoviedb.org/3/"
    const val API_KEY_INDEX = "&api_key="
    const val API_KEY_INDEX_SEARCH = "?api_key="
    const val API_KEY = "16979c3aca376088dd87ed8de4086b91"
    const val API_PERSON = "person/popular"
    const val API_TV_AIRING_TODAY = "tv/airing_today"
    const val API_TV_LATEST = "tv/latest"
    const val API_TV_ON_THE_AIR = "tv/on_the_air"
    const val API_TV_POPULAR = "tv/popular"
    const val API_TV_TOP_RATED = "tv/top_rated"
    const val API_COMPANY_DETAILS = "company/"
    const val API_PERSON_DETAILS = "person/"
    const val API_QUERY_COMPANY = "&query="

    const val API_LANGUAJE = "&language=en-US"
    const val API_PAGE = "&page="

    const val API_SEARCH_COMPANY = "search/company"
    const val API_SEARCH_MOVIE = "search/movie"
    const val API_SEARCH_PERSON = "search/person"
    const val API_SEARCH_TV = "search/tv"

    const val API_POPULAR_MOVIES = "discover/movie?sort_by=popularity.desc"
    const val API_CURRENT_MOVIES =
        "discover/movie?primary_release_date.gte=2014-09-15&primary_release_date.lte=2021-07-04"
    const val API_YEAR_DRAMA = "discover/movie?with_genres=18&primary_release_year=2014"
    const val API_TOM_CRUISE_MOVIES =
        "discover/movie?with_genres=878&with_cast=500&sort_by=vote_average.desc"

    const val IMAGES_URL = "https://image.tmdb.org/t/p/w500/"

}