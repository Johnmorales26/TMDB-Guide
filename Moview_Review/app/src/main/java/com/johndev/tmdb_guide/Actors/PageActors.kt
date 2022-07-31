package com.johndev.tmdb_guide.Actors

data class PageActors(
    var page: Int?,
    var results: List<Actor?>?,
    var total_pages: Int?,
    var total_results: Int?
)