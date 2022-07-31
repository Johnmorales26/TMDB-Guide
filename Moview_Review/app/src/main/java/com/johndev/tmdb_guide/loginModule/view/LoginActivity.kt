package com.johndev.tmdb_guide.loginModule.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.utils.Constans.API_AUTHENTICATION
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.API_SESION
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import com.johndev.tmdb_guide.databinding.ActivityLoginBinding
import com.johndev.tmdb_guide.loginModule.viewModel.LoginViewModel
import com.johndev.tmdb_guide.mainModule.view.MainActivity
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginContext = this
        setupViewModel()
        setupObservers()
        configureButtons()
    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.createRequestToken()
    }

    private fun setupObservers() {
        loginViewModel.getRequestToken().observe(this) { requestToken ->
            if (requestToken != null) {
                val params = HashMap<String,String>()
                params["request_token"] = requestToken.request_token.toString().trim()
                val jsonObject = (params as Map<*, *>?)?.let { JSONObject(it) }
                val url = URL_BASE + API_AUTHENTICATION + API_SESION + API_KEY_INDEX_SEARCH + API_KEY
                val queue = Volley.newRequestQueue(this)
                val request = JsonObjectRequest(Request.Method.POST,url,jsonObject,
                    { response ->
                        // Process the json
                        try {
                            //toastMSG("Response: $response")
                        }catch (e:Exception){
                            //toastMSG("Excepcion: $e")
                        }
                    }, { error ->
                        //toastMSG("Error: $error")
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
        }
    }

    fun toastMSG(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun configureButtons() {
        binding.btnSigIn.setOnClickListener {
            binding.containerMain.visibility = GONE
            binding.container.visibility = VISIBLE
            val fragment = LogInFragment()
            openFragment(fragment)
        }
        binding.btnGuestSession.setOnClickListener {
            //loginViewModel.createRequestToken()
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra(getString(R.string.key_guest_session), "invited")
            }
            startActivity(intent)
        }
        binding.tvLogIn.setOnClickListener {
            val uri = Uri.parse("https://www.themoviedb.org/signup")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.container.visibility = GONE
        binding.containerMain.visibility = VISIBLE
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    companion object {
        lateinit var loginContext: Context
        lateinit var loginViewModel: LoginViewModel
    }

}