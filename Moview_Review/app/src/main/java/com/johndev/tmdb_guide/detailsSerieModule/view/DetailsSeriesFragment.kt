package com.johndev.tmdb_guide.detailsSerieModule.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.johndev.tmdb_guide.databinding.FragmentDetailsBinding
import com.johndev.tmdb_guide.detailsMovieModule.view.DetailsMovieActivity
import com.johndev.tmdb_guide.detailsSerieModule.view.DetailSeriesActivity.Companion.detailsViewModel

class DetailsSeriesFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {
        detailsViewModel.getResult().observe(viewLifecycleOwner) { serie ->
            binding.let {
                it.tvTitle.text = serie.name.toString().trim()
                it.tvOverview.text = serie.overview.toString().trim()
                it.tvDateReleased.text = serie.first_air_date.toString().trim()
                //  Genres
                var genres = ""
                serie.genres?.forEach { genresLambda ->
                    genres += genresLambda.name + ", "
                }
                it.tvGenres.text = genres.trim()
                //  Production Companies
                var productionCompanies = ""
                serie.production_companies?.forEach { productionLambda ->
                    productionCompanies += productionLambda.name + ", "
                }
                it.tvProductionCompanie.text = productionCompanies.trim()
                //  Languajes
                var spokenLanguajes = ""
                serie.spoken_languages?.forEach { spokenLambda ->
                    spokenLanguajes += spokenLambda.name + ", "
                }
                it.tvSpokenLanguajes.text = spokenLanguajes.trim()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}