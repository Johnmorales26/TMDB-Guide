package com.johndev.tmdb_guide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.johndev.tmdb_guide.Actors.ActorsFragment
import com.johndev.tmdb_guide.MainPage.MainPageFragment
import com.johndev.tmdb_guide.SearchData.SearchActor.SearchActorFragment
import com.johndev.tmdb_guide.SearchData.SearchCompany.SearchCompanieFragment
import com.johndev.tmdb_guide.SearchData.SearchMovie.SearchMovieFragment
import com.johndev.tmdb_guide.SearchData.SearchTvShows.SearchTVFragment
import com.johndev.tmdb_guide.TV.MainPageTVFragment
import com.johndev.tmdb_guide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()
        setupHomePage()

        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            binding.drawerLayout.close()
            when (menuItem.itemId) {
                R.id.action_movie_theater -> {
                    configureTitle("Peliculas")
                    val fragment = MainPageFragment()
                    openFragment(fragment)
                }
                R.id.action_actors -> {
                    configureTitle("Actores")
                    val fragment = ActorsFragment()
                    openFragment(fragment)
                }
                R.id.action_tv -> {
                    configureTitle("TV")
                    val fragment = MainPageTVFragment()
                    openFragment(fragment)
                }
                R.id.action_search_companies -> {
                    configureTitle("Buscar Compañias")
                    val fragment = SearchCompanieFragment()
                    openFragment(fragment)
                }
                R.id.action_search_movie -> {
                    configureTitle("Buscar Peliculas")
                    val fragment = SearchMovieFragment()
                    openFragment(fragment)
                }
                R.id.action_search_person -> {
                    configureTitle("Buscar Personas")
                    val fragment = SearchActorFragment()
                    openFragment(fragment)
                }
                R.id.action_search_tv -> {
                    configureTitle("Buscar TV Shows")
                    val fragment = SearchTVFragment()
                    openFragment(fragment)
                }
            }
            true
        }
    }

    private fun setupHomePage() {
        val fragment = MainPageFragment()
        openFragment(fragment)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        with(binding) {
            configureTitle(getString(R.string.app_name))
            binding.topAppBar.setNavigationOnClickListener {
            /*var modalFragment = ModalNavigationDrawerFragment()
            modalFragment.show(supportFragmentManager.beginTransaction(), ModalNavigationDrawerFragment.TAG)*/
            }
        }
    }

    fun configureTitle(title: String) {
        binding.topAppBar.title = title
    }

}