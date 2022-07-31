package com.johndev.tmdb_guide.detailsSerieModule.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.johndev.tmdb_guide.OnTVListener
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.adapters.CardAdapter
import com.johndev.tmdb_guide.common.adapters.CardTVAdapter
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.databinding.FragmentSugerenciasBinding
import com.johndev.tmdb_guide.detailsMovieModule.view.DetailsMovieActivity
import com.johndev.tmdb_guide.detailsSerieModule.view.DetailSeriesActivity.Companion.detailsViewModel

class SugerenceSeriesFragment : Fragment(), OnTVListener {

    private var _binding: FragmentSugerenciasBinding? = null
    private val binding get() = _binding!!
    private lateinit var sugerencesAdapter: CardTVAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSugerenciasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureAdapter()
        setupObservers()
        getSugerences()
    }

    private fun getSugerences() {
        detailsViewModel.getResult().observe(viewLifecycleOwner) {
            detailsViewModel.consultSugerencesByID(it.id.toString().trim())
        }
    }

    private fun setupObservers() {
        detailsViewModel.getSimilarMovies().observe(viewLifecycleOwner) {
            configureAdapter(it)
        }
    }

    private fun configureAdapter(list: MutableList<Result>? = mutableListOf()) {
        var auxList = list
        if (auxList == null) {
            auxList = mutableListOf()
        }
        sugerencesAdapter = CardTVAdapter(auxList, this)
        binding.recyclerview.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = sugerencesAdapter
        }
    }

    override fun onTVListener(movie: Result, imgPoster: View) {
        val intent = Intent(context, DetailSeriesActivity::class.java).apply {
            putExtra(getString(R.string.key_id_passed), movie.id.toString())
        }
        val imgPair: Pair<View, String> = Pair.create(imgPoster, imgPoster.transitionName.toString())
        val options = ActivityOptions.makeSceneTransitionAnimation(activity, imgPair).toBundle()
        startActivity(intent, options)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}