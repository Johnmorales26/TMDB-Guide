package com.johndev.tmdb_guide.common.entities

data class PagesSearchMovies(
    var page: Int?,
    var results: List<MoviesSearch?>?,
    var total_pages: Int?,
    var total_results: Int?
)