package com.johndev.tmdb_guide.common.entities

data class RequestToken(
    var expires_at: String? = null,
    var request_token: String? = null,
    var success: Boolean? = null
)