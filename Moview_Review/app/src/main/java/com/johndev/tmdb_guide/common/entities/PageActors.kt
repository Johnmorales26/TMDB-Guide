package com.johndev.tmdb_guide.common.entities

data class PageActors(
    var page: Int?,
    var results: List<ActorEntity?>?,
    var total_pages: Int?,
    var total_results: Int?
)