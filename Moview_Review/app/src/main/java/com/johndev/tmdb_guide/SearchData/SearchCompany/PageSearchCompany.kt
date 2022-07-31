package com.johndev.tmdb_guide.SearchData.SearchCompany

data class PageSearchCompany(
    var page: Int?,
    var results: List<CompanySearch?>?,
    var total_pages: Int?,
    var total_results: Int?
)