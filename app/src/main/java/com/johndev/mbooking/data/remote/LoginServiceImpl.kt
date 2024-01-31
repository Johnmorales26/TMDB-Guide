package com.johndev.mbooking.data.remote

import com.johndev.mbooking.data.datasource.LoginService
import com.johndev.mbooking.data.model.AuthenticationResponse
import com.johndev.mbooking.data.model.TokenResponse
import retrofit2.HttpException
import retrofit2.Retrofit
import javax.inject.Inject

class LoginServiceImpl @Inject constructor(
    private val retrofit: Retrofit
) : LoginService {
    override suspend fun createRequestToken(key: String): TokenResponse {
        val service = retrofit.create(LoginService::class.java)
        return service.createRequestToken(key)
    }

    override suspend fun createSessionWithLogin(
        key: String,
        username: String,
        password: String,
        requestToken: String
    ): AuthenticationResponse {
        val service = retrofit.create(LoginService::class.java)
        return try {
            service.createSessionWithLogin(
                key,
                username,
                password,
                requestToken
            )
        } catch (e: HttpException) {
            println("-----> Status code: ${e.code()}")
            service.createSessionWithLogin(
                key,
                username,
                password,
                requestToken
            )
        }
    }


}