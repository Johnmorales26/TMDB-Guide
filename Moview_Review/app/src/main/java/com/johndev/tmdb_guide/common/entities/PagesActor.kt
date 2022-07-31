package com.johndev.tmdb_guide.common.entities

import com.johndev.tmdb_guide.common.entities.Result

data class PagesActor(
    var page: Int?,
    var results: List<ActorSearch>?,
    var total_pages: Int?,
    var total_results: Int?
)