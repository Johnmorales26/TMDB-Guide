package com.johndev.tmdb_guide.detailsSerieModule.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.adapters.CardAdapter
import com.johndev.tmdb_guide.common.adapters.SeasonsAdapter
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.common.entities.Seasons
import com.johndev.tmdb_guide.databinding.FragmentSeasonsBinding
import com.johndev.tmdb_guide.databinding.FragmentSugerenciasBinding
import com.johndev.tmdb_guide.detailsMovieModule.view.DetailsMovieActivity
import com.johndev.tmdb_guide.detailsSerieModule.view.DetailSeriesActivity.Companion.detailsViewModel

class SeasonsFragment : Fragment() {

    private var _binding: FragmentSeasonsBinding? = null
    private val binding get() = _binding!!
    private lateinit var seasonsAdapter: SeasonsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeasonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureAdapter()
        setupObservers()
        getSeasons()
    }

    private fun getSeasons() {
        detailsViewModel.getResult().observe(viewLifecycleOwner, Observer {
            detailsViewModel.consultSeasonsById(it.id.toString().trim())
        })
    }

    private fun setupObservers() {
        detailsViewModel.getSeasons().observe(viewLifecycleOwner, Observer {
            configureAdapter(it)
        })
    }

    private fun configureAdapter(list: MutableList<Seasons> = mutableListOf()) {
        seasonsAdapter = SeasonsAdapter(list)
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = seasonsAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}