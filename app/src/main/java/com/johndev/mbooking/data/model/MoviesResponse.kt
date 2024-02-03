package com.johndev.mbooking.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse (
    val page: Long,
    val results: List<Movies>,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("total_results")
    val totalResults: Long
)

data class Movies (
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val id: Long,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("poster_path")
    val posterPath: String
)