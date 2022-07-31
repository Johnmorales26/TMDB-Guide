package com.johndev.tmdb_guide.loginModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.tmdb_guide.common.entities.CreateSession
import com.johndev.tmdb_guide.common.entities.RequestToken
import com.johndev.tmdb_guide.common.entities.SessionWithLogin
import com.johndev.tmdb_guide.loginModule.model.LoginRepository
import com.johndev.tmdb_guide.loginModule.view.LoginActivity.Companion.loginContext
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = LoginRepository(loginContext)

    fun getRequestToken() = requestToken
    fun getSessionWithLogin() = createSessionWithLogin
    fun getSession() = createSessionRes

    fun createRequestToken() {
        viewModelScope.launch {
            requestToken.value = repository.createRequestToken()
        }
    }

    fun createSessionWithLogin(user: SessionWithLogin) {
        viewModelScope.launch {
            createSessionWithLogin.value = repository.createSessionWithLogin(user)
        }
    }

    fun createSession(requestToken: String) {
        viewModelScope.launch {
            createSessionRes.value = repository.createSession(requestToken)
        }
    }

    companion object {
        val requestToken = MutableLiveData<RequestToken>()
        val createSessionWithLogin = MutableLiveData<RequestToken>()
        val createSessionRes = MutableLiveData<CreateSession>()
    }

}