package com.johndev.tmdb_guide.common.entities

data class SessionWithLogin(
    var username: String,
    var password: String,
    var request_token: String
)