package com.johndev.mbooking.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.mbooking.R
import com.johndev.mbooking.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: LoginRepository) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun createSessionWithLogin(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val token = repository.createRequestToken()
                if (token.success && !_username.value.isNullOrEmpty() && !_password.value.isNullOrEmpty()) {
                    val response = repository.createSessionWithLogin(
                        //username = _username.value!!.trim(),
                        //password = _password.value!!.trim(),
                        username = "Johnmorales26",
                        password = "JAMT2610",
                        requestToken = token.requestToken
                    )
                    if (response.success) {
                        _message.value = context.getString(R.string.login_success_message)
                        onSuccess()
                    } else {
                        _message.value = context.getString(R.string.error_invalid_parameters)
                    }
                } else {
                    _message.value = context.getString(R.string.error_communicating_server)
                }
            } catch (e: HttpException) {
                // Manejar respuestas HTTP no exitosas aquí
                when (e.code()) {
                    401 -> {
                        _message.value = context.getString(R.string.error_invalid_credentials_401)
                    }
                    // Agrega más casos según sea necesario para manejar otros códigos de error.
                    else -> {
                        _message.value = context.getString(R.string.error_server, e.code())
                    }
                }
            } catch (e: Exception) {
                _message.value = context.getString(R.string.error_unknown, e.message)
            }
        }
    }

    fun updateForm(username: String, password: String) {
        _username.value = username
        _password.value = password
    }

}