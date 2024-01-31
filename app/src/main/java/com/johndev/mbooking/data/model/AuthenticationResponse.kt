package com.johndev.mbooking.data.model

import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    @SerializedName("success")
    val success: Boolean
)