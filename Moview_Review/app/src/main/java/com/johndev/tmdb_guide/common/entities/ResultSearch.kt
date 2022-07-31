package com.johndev.tmdb_guide.common.entities

data class ResultSearch(
    var id: Int,
    var backdrop_path: String?,
    var poster_path: String?,
    var title: String,
    var isMovie: Boolean
)