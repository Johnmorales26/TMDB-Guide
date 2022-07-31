package com.johndev.tmdb_guide.common.entities

data class PagesPopularMovies(
    var page: Int?,
    var results: List<MoviePopular?>?,
    var total_pages: Int?,
    var total_results: Int?
)