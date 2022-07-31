package com.johndev.tmdb_guide.loginModule.model

import android.content.Context
import com.johndev.tmdb_guide.common.entities.SessionWithLogin

class LoginRepository(val context: Context) {

    private val remoteData = LoginRemoteData(context)

    suspend fun createRequestToken() = remoteData.createRequestToken()

    suspend fun createSessionWithLogin(user: SessionWithLogin) = remoteData.createSessionWithLogin(user)

    suspend fun createSession(requestToken: String) = remoteData.createSession(requestToken)

}