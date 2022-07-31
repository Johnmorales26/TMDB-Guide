package com.johndev.tmdb_guide.splashModule.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.entities.SessionWithLogin
import com.johndev.tmdb_guide.databinding.ActivitySplashBinding
import com.johndev.tmdb_guide.loginModule.view.LoginActivity
import com.johndev.tmdb_guide.mainModule.view.MainActivity
import com.johndev.tmdb_guide.splashModule.viewModel.LoginViewModel
import org.json.JSONObject

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var sharedPreferences: SharedPreferences
    private var handler = Handler()
    private var isActive = false
    private var value = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginContext = this
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        setupViewModel()
        setupObservers()
        loadProgressBar()
    }

    private fun setupObservers() {
        loginViewModel.getRequestToken().observe(this) { requestToken ->
            if (requestToken != null) {
                val username = sharedPreferences.getString(getString(R.string.key_username), null)
                val password = sharedPreferences.getString(getString(R.string.key_password), null)
                val token = requestToken.request_token.toString().trim()
                saveToken(token)
                if (username != null && password != null) {
                    loginViewModel.createSessionWithLogin(SessionWithLogin(username, password, token))
                }
            }
        }
        loginViewModel.getSessionWithLogin().observe(this) { session ->
            if (session != null) {
                if (session.success == true) {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }
    }

    private fun saveToken(token: String) {
        sharedPreferences.edit {
            putString(getString(R.string.key_token), token)
            apply()
        }
    }

    private fun callApi(jsonObject: JSONObject, url: String) {
        val queue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(
            Request.Method.POST,url,jsonObject,
            { response ->
                println("My Response -> \n $response")
                // Process the json
                try {
                    toastMSG("Response: $response")
                }catch (e:Exception){
                    toastMSG("Excepcion: $e")
                }
            }, { error ->
                toastMSG("Error: $error")
            })
        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        queue.add(request)
    }

    private fun toastMSG(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.createRequestToken()
    }

    private fun loadProgressBar() {
        var progress = 0
        if (!isActive) {
            val thread = Thread {
                kotlin.run {
                    while (progress <= 100) {
                        handler.post {
                            kotlin.run {
                                binding.progressBar.progress = progress
                            }
                        }
                        try {
                            Thread.sleep(30)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                        if (progress == 100) {
                            val username = sharedPreferences.getString(getString(R.string.key_username), null)
                            val password = sharedPreferences.getString(getString(R.string.key_password), null)
                            if (username == null) {
                                startActivity(Intent(this, LoginActivity::class.java))
                            }
                        }
                        progress++
                        isActive = true
                    }
                }
            }
            thread.start()
        }
    }

    companion object {
        lateinit var loginContext: Context
        lateinit var loginViewModel: LoginViewModel
    }

}