package com.johndev.tmdb_guide.loginModule.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.entities.CreateSession
import com.johndev.tmdb_guide.common.entities.SessionWithLogin
import com.johndev.tmdb_guide.common.utils.hideKeyboard
import com.johndev.tmdb_guide.databinding.FragmentLogInBinding
import com.johndev.tmdb_guide.loginModule.view.LoginActivity.Companion.loginViewModel
import com.johndev.tmdb_guide.mainModule.view.MainActivity
import java.lang.NullPointerException

class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        setupObservers()
        configureButtons()
    }

    private fun setupObservers() {
        loginViewModel.getSessionWithLogin().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                try {
                    if (result.success!!) {
                        Toast.makeText(context, getString(R.string.msj_logging_in), Toast.LENGTH_SHORT).show()
                        loginViewModel.createSession(result.request_token.toString().trim())
                        loginViewModel.getSession().observe(viewLifecycleOwner) { createSesion ->
                            if (createSesion != null) {
                                try {
                                    if (createSesion.success!!){
                                        startApp(createSesion)
                                    }
                                } catch (e: NullPointerException) {
                                    println(e)
                                }
                            }
                        }
                    } else {
                        Toast.makeText(context, getString(R.string.msj_incorrect_data), Toast.LENGTH_SHORT).show()
                    }
                } catch (e: NullPointerException) {
                    Toast.makeText(context, getString(R.string.msj_incorrect_data), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveUserData(username: String, password: String) {
        sharedPreferences.edit {
            putString(getString(R.string.key_username), username)
            putString(getString(R.string.key_password), password)
            apply()
        }
    }

    private fun startApp(createSesion: CreateSession) {
        val intent = Intent(context, MainActivity::class.java).apply {
            putExtra(getString(R.string.key_create_success), createSesion.success)
            putExtra(getString(R.string.key_create_session_id), createSesion.session_id)
        }
        startActivity(intent)
    }

    private fun configureButtons() {
        binding.btnLogIn.setOnClickListener {
            if (validFields()) {
                val username = binding.etUsername.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()
                loginViewModel.getRequestToken().observe(viewLifecycleOwner) { requestToken ->
                    if (requestToken != null) {
                        val token = requestToken.request_token.toString().trim()
                        val session = SessionWithLogin(username, password, token)
                        hideKeyboard(requireContext(), binding.root)
                        val remember = binding.swRemember.isChecked
                        if (remember) {
                            saveUserData(username, password)
                        }
                        loginViewModel.createSessionWithLogin(session)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validFields(): Boolean{
        var isValid = true
        //  Evaluando Username
        if (binding.etUsername.text.isNullOrEmpty()){
            binding.tilUsername.run {
                error = getString(R.string.alert_required)
                requestFocus()
            }
            isValid = false
        } else {
            binding.tilUsername.error = null
        }
        //  Evaluando Username
        if (binding.etPassword.text.isNullOrEmpty()){
            binding.tilPassword.run {
                error = getString(R.string.alert_required)
                requestFocus()
            }
            isValid = false
        } else {
            binding.tilPassword.error = null
        }
        return isValid
    }

}