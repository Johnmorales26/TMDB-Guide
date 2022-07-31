package com.johndev.tmdb_guide.detailsMovieModule.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.johndev.tmdb_guide.OnMovieListener
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.adapters.CardAdapter
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.databinding.FragmentSugerenciasBinding
import com.johndev.tmdb_guide.detailsMovieModule.view.DetailsMovieActivity.Companion.detailsViewModel

class SugerenciasFragment : Fragment(), OnMovieListener {

    private var _binding: FragmentSugerenciasBinding? = null
    private val binding get() = _binding!!
    private lateinit var sugerencesAdapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSugerenciasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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

    private fun configureAdapter(list: MutableList<Result> = mutableListOf()) {
        sugerencesAdapter = CardAdapter(list, this)
        binding.recyclerview.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = sugerencesAdapter
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
}