package com.johndev.tmdb_guide.profileModule.view

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
import coil.load
import coil.transform.CircleCropTransformation
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.databinding.FragmentProfileBinding
import com.johndev.tmdb_guide.getImage
import com.johndev.tmdb_guide.loginModule.view.LoginActivity
import com.johndev.tmdb_guide.mainModule.view.MainActivity
import com.johndev.tmdb_guide.mainModule.view.MainActivity.Companion.accountViewModel

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        getPreferences()
        setupObservers()
        configureButtons()
    }

    private fun setupObservers() {
        accountViewModel.getSession().observe(viewLifecycleOwner) { createSession ->
            if (createSession != null) {
                accountViewModel.createAccount(createSession.session_id.toString().trim())
            }
        }
        accountViewModel.getAccount().observe(viewLifecycleOwner) { myAccount ->
            if (myAccount != null) {
                if (myAccount.username != null) {
                    binding.imgAccount.load(getImage(myAccount.avatar!!.tmdb!!.avatar_path.toString().trim())) {
                        crossfade(true)
                        placeholder(R.drawable.ic_cloud_download)
                        transformations(CircleCropTransformation())
                    }
                    binding.tvUsername.text = myAccount.username.toString().trim()
                    binding.tvName.text = myAccount.name.toString().trim()
                    binding.tvLanguaje.text = getString(R.string.tv_languaje_parameters, myAccount.iso_639_1.toString().trim())
                    binding.tvRegion.text = getString(R.string.tv_region_parameters, myAccount.iso_3166_1.toString().trim())
                }
            }
        }
    }

    private fun getPreferences() {
        val token = sharedPreferences.getString(getString(R.string.key_token), null)
        accountViewModel.createSession(token.toString().trim())
    }

    private fun configureButtons() {
        binding.btnSignOff.setOnClickListener {
            Toast.makeText(context, getString(R.string.msj_closing_session), Toast.LENGTH_SHORT).show()
            sharedPreferences.edit {
                putString(getString(R.string.key_username), null)
                putString(getString(R.string.key_password), null)
                apply()
            }
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}