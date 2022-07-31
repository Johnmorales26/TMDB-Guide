package com.johndev.tmdb_guide.MainPage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.johndev.tmdb_guide.Actors.KnownFor
import com.johndev.tmdb_guide.Constans
import com.johndev.tmdb_guide.DetailsMovie.DetailsMovieActivity
import com.johndev.tmdb_guide.Interfaces.OnPressedMovie
import com.johndev.tmdb_guide.MoviePopularTheaters.CardMovieAdapter
import com.johndev.tmdb_guide.PopularMovies.MoviePopular
import com.johndev.tmdb_guide.Provider.Services.MoviesService
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.databinding.FragmentMainPageBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainPageFragment : Fragment(), OnPressedMovie {

    private var _binding: FragmentMainPageBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CardMovieAdapter
    private lateinit var adapterCurrentCinema: CardMovieAdapter
    private lateinit var adapterCurrentDrama: CardMovieAdapter
    private lateinit var adapterTomsMovies: CardMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        lifecycleScope.launch(Dispatchers.Default) {
            inflateList()
        }
    }

    private fun setupRecyclerViews() {
        setupRecyclerView()
        setupRVPlayCinema()
        setupRVDrama()
        setupRVRatedR()
    }

    private suspend fun inflateList() = withContext(Dispatchers.Default) {
        val moviesService = MoviesService(activity, requireContext(), adapter)
        lifecycleScope.launch(Dispatchers.Default) {
            moviesService.getDataMovie(Constans.API_POPULAR_MOVIES)
        }
        val moviesServiceCurrentCinema = MoviesService(activity, requireContext(), adapterCurrentCinema)
        lifecycleScope.launch(Dispatchers.Default) {
            moviesServiceCurrentCinema.getDataMovie(Constans.API_CURRENT_MOVIES)
        }
        val moviesServiceCurrentDrama = MoviesService(activity, requireContext(), adapterCurrentDrama)
        lifecycleScope.launch(Dispatchers.Default) {
            moviesServiceCurrentDrama.getDataMovie(Constans.API_YEAR_DRAMA)
        }
        val moviesServiceRatedR = MoviesService(activity, requireContext(), adapterTomsMovies)
        lifecycleScope.launch(Dispatchers.Default) {
            moviesServiceRatedR.getDataMovie(Constans.API_TOM_CRUISE_MOVIES)
        }
    }

    private fun setupRecyclerView() {
        val data: MutableList<MoviePopular> = mutableListOf()
        binding.let {
            adapter = CardMovieAdapter(data, this)
            it.recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@MainPageFragment.adapter
            }
        }
    }

    private fun setupRVPlayCinema() {
        val data: MutableList<MoviePopular> = mutableListOf()
        binding.let {
            adapterCurrentCinema = CardMovieAdapter(data, this)
            it.rvPlayingCinema.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@MainPageFragment.adapterCurrentCinema
            }
        }
    }

    private fun setupRVDrama() {
        val data: MutableList<MoviePopular> = mutableListOf()
        binding.let {
            adapterCurrentDrama = CardMovieAdapter(data, this)
            it.rvDramaYear.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@MainPageFragment.adapterCurrentDrama
            }
        }
    }

    private fun setupRVRatedR() {
        val data: MutableList<MoviePopular> = mutableListOf()
        binding.let {
            adapterTomsMovies = CardMovieAdapter(data, this)
            it.rvTomsMovies.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@MainPageFragment.adapterTomsMovies
            }
        }
    }

    override fun OnMoviePressed(movie: MoviePopular) {
        val intent = Intent(context, DetailsMovieActivity::class.java).apply {
            putExtra(getString(R.string.key_movie_passed), movie.id.toString().trim())
        }
        startActivity(intent)
    }

    override fun OnMovieKnowPressed(movie: KnownFor) {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}