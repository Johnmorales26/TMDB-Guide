package com.johndev.tmdb_guide.common.entities

data class Account(
    var avatar: Avatar? = null,
    var id: Int? = null,
    var include_adult: Boolean? = null,
    var iso_3166_1: String? = null,
    var iso_639_1: String? = null,
    var name: String? = null,
    var username: String? = null
)