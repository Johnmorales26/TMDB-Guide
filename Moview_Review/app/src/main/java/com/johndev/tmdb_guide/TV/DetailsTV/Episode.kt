package com.johndev.tmdb_guide.TV.DetailsTV

data class Episode(
    var air_date: String?,
    var episode_number: Int?,
    var id: Int?,
    var name: String?,
    var overview: String?,
    var production_code: String?,
    var runtime: Int?,
    var season_number: Int?,
    var still_path: String?,
    var vote_average: Double?,
    var vote_count: Int?
)