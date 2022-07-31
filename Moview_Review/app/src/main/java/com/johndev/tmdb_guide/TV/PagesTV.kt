package com.johndev.tmdb_guide.TV

data class PagesTV(
    var page: Int?,
    var results: List<TV?>?,
    var total_pages: Int?,
    var total_results: Int?
)