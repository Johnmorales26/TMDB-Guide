package com.johndev.mbooking.data.datasource

import com.johndev.mbooking.data.model.AuthenticationResponse
import com.johndev.mbooking.data.model.TokenResponse
import com.johndev.mbooking.data.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginService {

    @Headers("Content-Type: application/json")
    @GET("${Constants.ENDPOINT_AUTHENTICATION}${Constants.ENDPOINT_TOKEN}")
    suspend fun createRequestToken(
        @Query(Constants.API_KEY_PARAM) key: String
    ): TokenResponse

    @Headers("Content-Type: application/json")
    @POST("${Constants.ENDPOINT_AUTHENTICATION}${Constants.ENDPOINT_VALIDATE_LOGIN}")
    suspend fun createSessionWithLogin(
        @Query(Constants.API_KEY_PARAM) key: String,
        @Query(Constants.PARAM_USERNAME) username: String,
        @Query(Constants.PARAM_PASSWORD) password: String,
        @Query(Constants.PARAM_REQUEST_TOKEN) requestToken: String
    ): AuthenticationResponse

}