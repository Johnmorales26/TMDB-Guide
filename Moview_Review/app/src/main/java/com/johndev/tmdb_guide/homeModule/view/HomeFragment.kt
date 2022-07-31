package com.johndev.tmdb_guide.homeModule.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.johndev.tmdb_guide.OnMovieListener
import com.johndev.tmdb_guide.OnTVListener
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.API_MOVIE_NOW_PLAYING
import com.johndev.tmdb_guide.common.utils.Constans.API_MOVIE_POPULAR
import com.johndev.tmdb_guide.common.utils.Constans.API_MOVIE_TOP_RATED
import com.johndev.tmdb_guide.common.utils.Constans.API_MOVIE_UPCOMING
import com.johndev.tmdb_guide.common.utils.Constans.API_PAGE
import com.johndev.tmdb_guide.common.utils.Constans.API_TV_AIRING_TODAY
import com.johndev.tmdb_guide.common.utils.Constans.API_TV_ON_THE_AIR
import com.johndev.tmdb_guide.common.utils.Constans.API_TV_POPULAR
import com.johndev.tmdb_guide.common.utils.Constans.API_TV_TOP_RATED
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import com.johndev.tmdb_guide.common.adapters.CardAdapter
import com.johndev.tmdb_guide.common.adapters.CardTVAdapter
import com.johndev.tmdb_guide.common.adapters.MoviesPremiresAdapter
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.databinding.FragmentHomeBinding
import com.johndev.tmdb_guide.detailsMovieModule.view.DetailsMovieActivity
import com.johndev.tmdb_guide.getPremires
import kotlinx.coroutines.launch
import android.util.Pair
import androidx.lifecycle.lifecycleScope
import com.johndev.tmdb_guide.detailsSerieModule.view.DetailSeriesActivity

class HomeFragment : Fragment(), OnMovieListener, OnTVListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var premiresAdapter: MoviesPremiresAdapter
    //  Movies
    private lateinit var broadNowAdapter: CardAdapter
    private lateinit var topRatedAdapter: CardAdapter
    private lateinit var upcomingAdapter: CardAdapter
    //  TV
    private lateinit var airTodayAdapter: CardTVAdapter
    private lateinit var onTheAirAdapter: CardTVAdapter
    private lateinit var popularAdapter: CardTVAdapter
    private lateinit var topRatedTVAdapter: MoviesPremiresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureAdapters()
        lifecycleScope.launch {
            val urlPremires = URL_BASE + API_MOVIE_POPULAR + API_KEY_INDEX_SEARCH + API_KEY + API_PAGE + "1"
            getPremires(requireContext(), premiresAdapter, urlPremires)
            val urlBroadNow = URL_BASE + API_MOVIE_NOW_PLAYING + API_KEY_INDEX_SEARCH + API_KEY + API_PAGE + "1"
            getPremires(requireContext(), broadNowAdapter, urlBroadNow)
            val urlAirToday = URL_BASE + API_TV_AIRING_TODAY + API_KEY_INDEX_SEARCH + API_KEY + API_PAGE + "1"
            getPremires(requireContext(), airTodayAdapter, urlAirToday)
            val urlTopRated = URL_BASE + API_MOVIE_TOP_RATED + API_KEY_INDEX_SEARCH + API_KEY + API_PAGE + "1"
            getPremires(requireContext(), topRatedAdapter, urlTopRated)
            val urlOnTheAir = URL_BASE + API_TV_ON_THE_AIR + API_KEY_INDEX_SEARCH + API_KEY + API_PAGE + "1"
            getPremires(requireContext(), onTheAirAdapter, urlOnTheAir)
            val urlUpcoming = URL_BASE + API_MOVIE_UPCOMING + API_KEY_INDEX_SEARCH + API_KEY + API_PAGE + "1"
            getPremires(requireContext(), upcomingAdapter, urlUpcoming)
            val urlPopular = URL_BASE + API_TV_POPULAR + API_KEY_INDEX_SEARCH + API_KEY + API_PAGE + "1"
            getPremires(requireContext(), popularAdapter, urlPopular)
            val urlTopRatedTV = URL_BASE + API_TV_TOP_RATED + API_KEY_INDEX_SEARCH + API_KEY + API_PAGE + "1"
            getPremires(requireContext(), topRatedTVAdapter, urlTopRatedTV)
        }
    }

    private fun configureAdapters() {
        premiresAdapter = MoviesPremiresAdapter(mutableListOf(), this)
        binding.recyclerviewPremires.apply {
            //layoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = premiresAdapter
        }
        broadNowAdapter = CardAdapter(mutableListOf(), this)
        binding.recyclerviewBroadNow.apply {
            //layoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = broadNowAdapter
        }
        airTodayAdapter = CardTVAdapter(mutableListOf(), this)
        binding.recyclerviewAirToday.apply {
            //layoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = airTodayAdapter
        }
        topRatedAdapter = CardAdapter(mutableListOf(), this)
        binding.recyclerviewTopRated.apply {
            //layoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedAdapter
        }
        onTheAirAdapter = CardTVAdapter(mutableListOf(), this)
        binding.recyclerviewOnTheAir.apply {
            //layoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = onTheAirAdapter
        }
        upcomingAdapter = CardAdapter(mutableListOf(), this)
        binding.recyclerviewUpcoming.apply {
            //layoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = upcomingAdapter
        }
        popularAdapter = CardTVAdapter(mutableListOf(), this)
        binding.recyclerviewPopular.apply {
            //layoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }
        topRatedTVAdapter = MoviesPremiresAdapter(mutableListOf(), this)
        binding.recyclerviewTopRatedTV.apply {
            //layoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedTVAdapter
        }
    }

    override fun onMovieListener(movie: Result, imgPoster: View) {
        val intent = Intent(context, DetailsMovieActivity::class.java).apply {
            putExtra(getString(R.string.key_id_passed), movie.id.toString())
        }
        val imgPair: Pair<View, String> = Pair.create(imgPoster, imgPoster.transitionName.toString())
        val options = ActivityOptions.makeSceneTransitionAnimation(activity, imgPair).toBundle()
        startActivity(intent, options)
    }

    override fun onTVListener(movie: Result, imgPoster: View) {
        val intent = Intent(context, DetailSeriesActivity::class.java).apply {
            putExtra(getString(R.string.key_id_series_passed), movie.id.toString())
        }
        val imgPair: Pair<View, String> = Pair.create(imgPoster, imgPoster.transitionName.toString())
        val options = ActivityOptions.makeSceneTransitionAnimation(activity, imgPair).toBundle()
        startActivity(intent, options)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}