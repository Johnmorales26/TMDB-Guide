package com.johndev.mbooking.data.repository

import com.johndev.mbooking.data.datasource.LoginService
import com.johndev.mbooking.data.model.AuthenticationResponse
import com.johndev.mbooking.data.model.TokenResponse
import com.johndev.mbooking.data.utils.Constants
import com.johndev.mbooking.di.RemoteData
import javax.inject.Inject

class LoginRepository @Inject constructor(
    @RemoteData private val service: LoginService
) {

    suspend fun createRequestToken(): TokenResponse {
        return service.createRequestToken(Constants.API_KEY_VALUE)
    }

    suspend fun createSessionWithLogin(
        username: String,
        password: String,
        requestToken: String
    ): AuthenticationResponse {
        return service.createSessionWithLogin(
            Constants.API_KEY_VALUE,
            username = username,
            password = password,
            requestToken = requestToken
        )
    }

}