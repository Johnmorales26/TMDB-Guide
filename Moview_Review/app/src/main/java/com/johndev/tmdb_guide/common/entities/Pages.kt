package com.johndev.tmdb_guide.common.entities

import com.johndev.tmdb_guide.common.entities.Result

data class Pages(
    var page: Int?,
    var results: List<Result>?,
    var total_pages: Int?,
    var total_results: Int?
)