package com.johndev.tmdb_guide.common.entities

data class ActorSearch(
    var adult: Boolean?,
    var gender: Int?,
    var id: Int?,
    var known_for: List<KnownFor?>?,
    var known_for_department: String?,
    var name: String?,
    var popularity: Double?,
    var profile_path: String?
)