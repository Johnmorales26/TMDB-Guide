package com.johndev.tmdb_guide.TV.DetailsTV

data class Seasons(
    var air_date: String?,
    var episode_count: Int?,
    var id: Int?,
    var name: String?,
    var networks: List<Network?>?,
    var overview: String?,
    var poster_path: String?,
    var season_number: Int?
)