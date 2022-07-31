package com.johndev.tmdb_guide.mainModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.tmdb_guide.common.entities.Account
import com.johndev.tmdb_guide.common.entities.CreateSession
import com.johndev.tmdb_guide.loginModule.model.LoginRepository
import com.johndev.tmdb_guide.loginModule.viewModel.LoginViewModel
import com.johndev.tmdb_guide.mainModule.model.AccountRepository
import com.johndev.tmdb_guide.mainModule.view.MainActivity.Companion.searchContext
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel() {

    private val repositoryAccount = AccountRepository(searchContext)

    fun getSession() = createSessionRes
    fun getAccount() = account

    fun createSession(requestToken: String) {
        viewModelScope.launch {
            createSessionRes.value = repositoryAccount.createSession(requestToken)
        }
    }

    fun createAccount(sessionId: String) {
        viewModelScope.launch {
            account.value = repositoryAccount.getDetailsAccount(sessionId)
        }
    }

    companion object {
        val createSessionRes = MutableLiveData<CreateSession>()
        val account = MutableLiveData<Account>()
    }

}