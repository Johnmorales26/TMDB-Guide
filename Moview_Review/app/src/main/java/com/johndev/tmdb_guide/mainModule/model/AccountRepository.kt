package com.johndev.tmdb_guide.mainModule.model

import android.content.Context

class AccountRepository(val context: Context) {

    private val remoteData = AccountRemoteData(context)

    suspend fun createSession(requestToken: String) = remoteData.createSession(requestToken)

    suspend fun getDetailsAccount(sessonId: String) = remoteData.getDetailsAccount(sessonId)

}