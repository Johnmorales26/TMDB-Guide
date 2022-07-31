package com.johndev.tmdb_guide.loginModule.model

import android.content.Context
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.entities.CreateSession
import com.johndev.tmdb_guide.common.entities.RequestToken
import com.johndev.tmdb_guide.common.entities.SessionWithLogin
import com.johndev.tmdb_guide.common.utils.Constans.API_AUTHENTICATION
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.API_SESION
import com.johndev.tmdb_guide.common.utils.Constans.API_TOKEN
import com.johndev.tmdb_guide.common.utils.Constans.API_VALIDATE_LOGIN
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import com.johndev.tmdb_guide.loginModule.viewModel.LoginViewModel.Companion.createSessionRes
import com.johndev.tmdb_guide.loginModule.viewModel.LoginViewModel.Companion.createSessionWithLogin
import com.johndev.tmdb_guide.loginModule.viewModel.LoginViewModel.Companion.requestToken
import org.json.JSONObject

class LoginRemoteData(val context: Context) {

    fun createRequestToken(): RequestToken? {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = URL_BASE + API_AUTHENTICATION + API_TOKEN + API_KEY_INDEX_SEARCH + API_KEY
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val gson = Gson()
                val rqToken = gson.fromJson(response, RequestToken::class.java)
                requestToken.value = rqToken
            },
            { error ->

            })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return null
    }

    fun createSession(requestToken: String): CreateSession? {
        val params = HashMap<String,String>()
        params["request_token"] = requestToken
        val jsonObject = (params as Map<*, *>?)?.let { JSONObject(it) }
        val url = URL_BASE + API_AUTHENTICATION + API_SESION + API_KEY_INDEX_SEARCH + API_KEY
        println(jsonObject)
        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            { response ->
                try {
                    val rqToken = CreateSession(response["session_id"].toString(), response["success"] as Boolean)
                    createSessionRes.value = rqToken
                }catch (e:Exception){
                    createSessionWithLogin.value = RequestToken()
                }
            }, { error ->
                createSessionWithLogin.value = RequestToken()
            })
        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        queue.add(request)
        return null
    }

    fun createSessionWithLogin(user: SessionWithLogin): RequestToken? {
        val params = HashMap<String,String>()
        params["username"] = user.username.trim()
        params["password"] = user.password.trim()
        params["request_token"] = user.request_token.trim()
        val jsonObject = (params as Map<*, *>?)?.let { JSONObject(it) }
        val url = URL_BASE + API_AUTHENTICATION + API_VALIDATE_LOGIN + API_KEY_INDEX_SEARCH + API_KEY
        println(url)
        println(jsonObject)
        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(Request.Method.POST,url,jsonObject,
            { response ->
                // Process the json
                try {
                    val rqToken = RequestToken(response["expires_at"].toString(), response["request_token"].toString(),
                        response["success"] as Boolean?)
                    createSessionWithLogin.value = rqToken
                }catch (e:Exception){
                    createSessionWithLogin.value = RequestToken()
                }
            }, { error ->
                createSessionWithLogin.value = RequestToken()
            })
        // Volley request policy, only one time request to avoid duplicate transaction
        request.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            // 0 means no retry
            0, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES = 2
            1f // DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        queue.add(request)
        return null
    }

}