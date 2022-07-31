package com.johndev.tmdb_guide.detailsMovieModule.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.johndev.tmdb_guide.databinding.FragmentDetailsBinding
import com.johndev.tmdb_guide.detailsMovieModule.view.DetailsMovieActivity.Companion.detailsViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsViewModel.getResult().observe(viewLifecycleOwner) { movie ->
            binding.let {
                //  Title
                it.tvTitle.text = movie.title.toString().trim()
                //  Overview
                it.tvOverview.text = movie.overview.toString().trim()
                //  Released Date
                it.tvDateReleased.text = movie.release_date.toString().trim()
                //  Genres
                var genres = ""
                movie.genres?.forEach { genresLambda ->
                    genres += genresLambda.name + ", "
                }
                it.tvGenres.text = genres.trim()
                //  Production Companie
                var productionCompanie = ""
                movie.production_companies?.forEach { productionCompanies ->
                    productionCompanie += productionCompanies.name + ", "
                }
                it.tvProductionCompanie.text = productionCompanie.trim()
                //  Spoken Languajes
                var spokenLanguajes = ""
                movie.spoken_languages?.forEach { spokenLanguaje ->
                    spokenLanguajes += spokenLanguaje.name + ", "
                }
                it.tvSpokenLanguajes.text = spokenLanguajes.trim()
            }
        }
    }

}