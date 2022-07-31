package com.johndev.tmdb_guide.detailsActorModule.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.johndev.tmdb_guide.OnMovieListener
import com.johndev.tmdb_guide.OnTVListener
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.adapters.CardAdapter
import com.johndev.tmdb_guide.common.adapters.CardTVAdapter
import com.johndev.tmdb_guide.common.adapters.PersonAdapter
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.common.entities.ResultPerson
import com.johndev.tmdb_guide.databinding.ActivityDetailsActorBinding
import com.johndev.tmdb_guide.detailsActorModule.viewModel.DetailsActorViewModel
import com.johndev.tmdb_guide.detailsMovieModule.view.DetailsMovieActivity
import com.johndev.tmdb_guide.detailsMovieModule.viewModel.DetailsViewModel
import com.johndev.tmdb_guide.getImage

class DetailsActorActivity : AppCompatActivity(), OnMovieListener, OnTVListener {

    private lateinit var binding: ActivityDetailsActorBinding
    private lateinit var detailsViewModel: DetailsActorViewModel
    private lateinit var movieAdapter: CardAdapter
    private lateinit var tvAdapter: CardTVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsActorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        actorAcContext = this
        setupRecyclerview()
        setupRecyclerviewTV()
        setupAppBar()
        setupViewModel()
        setupObservers()
        getActor()
    }

    private fun setupAppBar() {
        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupViewModel() {
        detailsViewModel = ViewModelProvider(this)[DetailsActorViewModel::class.java]
    }

    private fun setupObservers() {
        detailsViewModel.getResult().observe(this) { actor ->
            if (actor == null) {
                binding.tvName.text = getString(R.string.msj_does_not_exit)
            } else {
                if (actor.name != null) {
                    binding.let {
                        //  Image
                        it.imgProfile.load(getImage(actor.profile_path!!.trim())) {
                            crossfade(true)
                            placeholder(R.drawable.ic_cloud_download)
                            transformations(CircleCropTransformation())
                        }
                        //  Title
                        it.topAppBar.title = actor.name.toString().trim()
                        it.tvName.text = actor.name.toString().trim()
                        //  Known For
                        it.tvKnownFor.text = actor.known_for_department.toString().trim()
                        //  Birthplace
                        it.tvBirthplace.text = actor.place_of_birth.toString().trim()
                        //  Date Of Birth
                        it.tvDateBirth.text = actor.birthday.toString().trim()
                        //  Biography
                        it.tvBiography.text = actor.biography.toString().trim()
                    }
                }
            }
        }
        detailsViewModel.getResultMovies().observe(this) { movies ->
            if (movies != null) {
                binding.recyclerviewMovies.visibility = VISIBLE
                setupRecyclerview(movies)
            }
        }
        detailsViewModel.getResultTV().observe(this) { tv ->
            if (tv != null) {
                binding.recyclerviewTV.visibility = VISIBLE
                setupRecyclerviewTV(tv)
            }
        }
    }

    private fun setupRecyclerview(listMovies: MutableList<Result> = mutableListOf()) {
        movieAdapter = CardAdapter(listMovies, this)
        binding.recyclerviewMovies.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = movieAdapter
        }
    }

    private fun setupRecyclerviewTV(listMovies: MutableList<Result> = mutableListOf()) {
        tvAdapter = CardTVAdapter(listMovies, this)
        binding.recyclerviewTV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = tvAdapter
        }
    }

    private fun getActor() {
        val id = intent.getStringExtra(getString(R.string.key_actor_id))
        detailsViewModel.consultActorByID(id.toString().trim())
        detailsViewModel.consultMoviesFromActorById(id.toString().trim())
        detailsViewModel.consultTVFromActorById(id.toString().trim())
    }

    override fun onMovieListener(movie: Result, imgPoster: View) {

    }

    override fun onTVListener(movie: Result, imgPoster: View) {

    }

    companion object {
        lateinit var actorAcContext: Context
    }

}