package com.johndev.tmdb_guide.mainModule.view

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.johndev.tmdb_guide.*
import com.johndev.tmdb_guide.celebritiesModule.view.CelebritiesFragment
import com.johndev.tmdb_guide.celebritiesModule.viewModel.ActorViewModel
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.common.entities.ResultSearch
import com.johndev.tmdb_guide.databinding.ActivityMainBinding
import com.johndev.tmdb_guide.homeModule.view.HomeFragment
import com.johndev.tmdb_guide.mainModule.viewModel.AccountViewModel
import com.johndev.tmdb_guide.profileModule.view.ProfileFragment
import com.johndev.tmdb_guide.searchModule.view.SearchFragment
import com.johndev.tmdb_guide.searchModule.viewModel.SearchViewModel
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchContext = this
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        setupViewModel()
        receivingData()
        setupBottomNavigation()
        if (isOnlineNet()) {
            binding.bottomNavigation.selectedItemId = R.id.action_home
        } else {
            val fragment = NotConectedFragment()
            openFragment(fragment)
        }
    }

    private fun receivingData() {
        val username = sharedPreferences.getString(getString(R.string.key_username), null)
        val guest = intent.getStringExtra(getString(R.string.key_guest_session))
        if (guest != "invited") {
            Toast.makeText(this, getString(R.string.msj_welcome_user, username), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.msj_welcome_user, guest), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupViewModel() {
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]
        actorViewModel = ViewModelProvider(this)[ActorViewModel::class.java]
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    val fragment = HomeFragment()
                    openFragment(fragment)
                    true
                }
                R.id.action_search -> {
                    val fragment = SearchFragment()
                    openFragment(fragment)
                    true
                }
                R.id.action_celebrities -> {
                    val fragment = CelebritiesFragment()
                    openFragment(fragment)
                    true
                }
                R.id.action_profile -> {
                    val guest = intent.getStringExtra(getString(R.string.key_guest_session))
                    if (guest != "invited") {
                        val fragment = ProfileFragment()
                        openFragment(fragment)
                    } else {
                        val fragment = HomeFragment()
                        openFragment(fragment)
                        Toast.makeText(this, "Inicie Sesion", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                else -> {
                    val fragment = HomeFragment()
                    openFragment(fragment)
                    true
                }
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun isOnlineNet(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    companion object {
        var isNull = true
        lateinit var searchMovies: MutableList<ResultSearch>
        lateinit var searchContext: Context
        lateinit var searchViewModel: SearchViewModel
        lateinit var accountViewModel: AccountViewModel
        lateinit var actorViewModel: ActorViewModel
    }

}