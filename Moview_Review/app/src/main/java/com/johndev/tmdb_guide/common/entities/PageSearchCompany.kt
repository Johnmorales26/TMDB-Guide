package com.johndev.tmdb_guide.common.entities

data class PageSearchCompany(
    var page: Int?,
    var results: List<CompanySearch?>?,
    var total_pages: Int?,
    var total_results: Int?
)