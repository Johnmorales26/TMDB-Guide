package com.johndev.tmdb_guide.common.entities

import com.johndev.tmdb_guide.common.entities.Result

data class PagesSearch(
    var page: Int?,
    var results: List<ResultSearch>?,
    var total_pages: Int?,
    var total_results: Int?
)