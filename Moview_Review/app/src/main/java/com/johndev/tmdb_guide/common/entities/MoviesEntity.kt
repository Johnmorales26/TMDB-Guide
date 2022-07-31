package com.johndev.tmdb_guide.common.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "MoviesEntity", indices = [Index(value = ["id"], unique = true)])
data class MoviesEntity(
    var adult: Boolean? = null,
    var backdrop_path: String? = null,
    var budget: Int? = null,
    //var genres: List<Genre?>? = null,
    var genres: String? = null,
    var homepage: String? = null,
    @PrimaryKey var id: Int? = null,
    var imdb_id: String? = null,
    var original_language: String? = null,
    var original_title: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var poster_path: String? = null,
    //var production_companies: List<ProductionCompanies?>? = null,
    var production_companies: String? = null,
    //var production_countries: List<Any?>? = null,
    var production_countries: String? = null,
    var release_date: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
    //var spoken_languages: List<SpokenLanguages?>? = null,
    var spoken_languages: String? = null,
    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var vote_average: Double? = null,
    var vote_count: Int? = null
)