package com.johndev.tmdb_guide.mainModule.model

import android.content.Context
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.entities.*
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.common.utils.Constans.API_ACCOUNT
import com.johndev.tmdb_guide.common.utils.Constans.API_INDEX_SESSION_ID
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import com.johndev.tmdb_guide.detailsMovieModule.viewModel.DetailsViewModel
import com.johndev.tmdb_guide.loginModule.viewModel.LoginViewModel
import com.johndev.tmdb_guide.mainModule.viewModel.AccountViewModel.Companion.account
import com.johndev.tmdb_guide.mainModule.viewModel.AccountViewModel.Companion.createSessionRes
import org.json.JSONObject

class AccountRemoteData(val context: Context) {

    fun createSession(requestToken: String): CreateSession? {
        val params = HashMap<String,String>()
        params["request_token"] = requestToken
        val jsonObject = (params as Map<*, *>?)?.let { JSONObject(it) }
        val url = Constans.URL_BASE + Constans.API_AUTHENTICATION + Constans.API_SESION + Constans.API_KEY_INDEX_SEARCH + Constans.API_KEY
        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(
            Request.Method.POST,url,jsonObject,
            { response ->
                // Process the json
                try {
                    val rqToken = CreateSession(response["session_id"].toString(), response["success"] as Boolean)
                    createSessionRes.value = rqToken
                }catch (e:Exception){
                    createSessionRes.value = CreateSession()
                }
            }, { error ->
                createSessionRes.value = CreateSession()
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

    //

    fun getDetailsAccount(sessonId: String): Account {
        val acount2 = Account()
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(context)
        val url = URL_BASE + API_ACCOUNT + API_KEY_INDEX_SEARCH + API_KEY + API_INDEX_SESSION_ID + sessonId
        val gson = Gson()
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val acount = gson.fromJson(response, Account::class.java)
            account.value = acount
        }, { error ->
            println("Error en el link - $url")
        })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
        return acount2
    }

}