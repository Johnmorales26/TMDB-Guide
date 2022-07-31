package com.johndev.tmdb_guide.TV.DetailsTV

import com.johndev.tmdb_guide.common.entities.Genre
import com.johndev.tmdb_guide.common.entities.ProductionCompanies

data class TvDetails(
    var adult: Boolean?,
    var backdrop_path: String?,
    var created_by: List<Any?>?,
    var episode_run_time: List<Any?>?,
    var first_air_date: String?,
    var genres: List<Genre?>?,
    var homepage: String?,
    var id: Int?,
    var in_production: Boolean?,
    var languages: List<Any?>?,
    var last_air_date: String?,
    var last_episode_to_air: Episode?,
    var name: String?,
    var networks: List<Any?>?,
    var next_episode_to_air: Episode?,
    var number_of_episodes: Int?,
    var number_of_seasons: Int?,
    var origin_country: List<Any?>?,
    var original_language: String?,
    var original_name: String?,
    var overview: String?,
    var popularity: Double?,
    var poster_path: String?,
    var production_companies: List<ProductionCompanies?>?,
    var production_countries: List<Any?>?,
    var seasons: List<Seasons?>?,
    var spoken_languages: List<Any?>?,
    var status: String?,
    var tagline: String?,
    var type: String?,
    var vote_average: Double?,
    var vote_count: Int?
)