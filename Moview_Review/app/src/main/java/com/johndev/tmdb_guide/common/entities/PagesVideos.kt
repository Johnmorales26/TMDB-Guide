package com.johndev.tmdb_guide.common.entities

import com.johndev.tmdb_guide.common.entities.Result

data class PagesVideos(
    var page: Int?,
    var results: List<Videos>?,
    var total_pages: Int?,
    var total_results: Int?
)