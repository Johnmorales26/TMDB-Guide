package com.johndev.tmdb_guide.DetailsCompany

data class Company(
    var description: String? = "",
    var headquarters: String? = "",
    var homepage: String? = "",
    var id: Int? = 0,
    var logo_path: String? = "",
    var name: String? = "",
    var origin_country: String? = "",
    var parent_company: String? = ""
)