package com.johndev.tmdb_guide.common.entities

data class Videos(
    var id: String?,
    var iso_3166_1: String?,
    var iso_639_1: String?,
    var key: String?,
    var name: String?,
    var official: Boolean?,
    var published_at: String?,
    var site: String?,
    var size: Int?,
    var type: String?
)