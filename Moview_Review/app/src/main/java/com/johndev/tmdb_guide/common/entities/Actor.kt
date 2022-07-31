package com.johndev.tmdb_guide.common.entities

data class Actor(
    var adult: Boolean? = null,
    var also_known_as: List<String?>? = null,
    var biography: String? = null,
    var birthday: String? = null,
    var deathday: Any? = null,
    var gender: Int? = null,
    var homepage: Any? = null,
    var id: Int? = null,
    var imdb_id: String? = null,
    var known_for_department: String? = null,
    var name: String? = null,
    var place_of_birth: String? = null,
    var popularity: Double? = null,
    var profile_path: String? = null
)