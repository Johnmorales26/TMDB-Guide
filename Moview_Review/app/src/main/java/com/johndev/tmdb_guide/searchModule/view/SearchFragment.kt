package com.johndev.tmdb_guide.searchModule.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Pair
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.johndev.tmdb_guide.OnMovieListener
import com.johndev.tmdb_guide.OnSearchListener
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.adapters.ResultSearchAdapter
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.common.entities.ResultSearch
import com.johndev.tmdb_guide.databinding.FragmentSearchBinding
import com.johndev.tmdb_guide.detailsMovieModule.view.DetailsMovieActivity
import com.johndev.tmdb_guide.detailsSerieModule.view.DetailSeriesActivity
import com.johndev.tmdb_guide.mainModule.view.MainActivity.Companion.isNull
import com.johndev.tmdb_guide.mainModule.view.MainActivity.Companion.searchMovies
import com.johndev.tmdb_guide.mainModule.view.MainActivity.Companion.searchViewModel
import java.util.*

class SearchFragment : Fragment(), OnSearchListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieSearchAdapter: ResultSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
        configureSearching()
        setupObservers()
    }

    private fun setupRecyclerview(listMovies: MutableList<ResultSearch> = mutableListOf()) {
        movieSearchAdapter = ResultSearchAdapter(listMovies, this)
        binding.recyclerview.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = movieSearchAdapter
        }
    }

    private fun setupObservers() {
        searchViewModel.getResult().observe(viewLifecycleOwner) { movieSearch ->
            if (movieSearch == null) {
                isNull = true
            } else {
                isNull = false
                movieSearch.forEach {
                    movieSearchAdapter.add(it)
                }
            }
        }
        searchViewModel.getResultSeries().observe(viewLifecycleOwner) { serieSearch ->
            if (serieSearch == null) {
                isNull = true
            } else {
                isNull = false
                serieSearch.forEach {
                    movieSearchAdapter.add(it)
                }
            }
        }
        //setupRecyclerview(searchList)
    }

    private fun configureSearching() {
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString().trim() == "") {
                    binding.fab.visibility = GONE
                    setupRecyclerview()
                } else {
                    searchViewModel.searchMoviesByQuery(s.toString().trim())
                    searchViewModel.searchSeriesByQuery(s.toString().trim())
                    if (isNull) {
                        setupRecyclerview()
                        binding.fab.visibility = GONE
                    } else {
                        binding.fab.visibility = VISIBLE
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onSearchListener(search: ResultSearch, imgPoster: View) {
        if (search.isMovie) {
            val intent = Intent(context, DetailsMovieActivity::class.java).apply {
                putExtra(getString(R.string.key_id_passed), search.id.toString())
            }
            val imgPair: Pair<View, String> = Pair.create(imgPoster, imgPoster.transitionName.toString())
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, imgPair).toBundle()
            startActivity(intent, options)
        } else {
            val intent = Intent(context, DetailSeriesActivity::class.java).apply {
                putExtra(getString(R.string.key_id_series_passed), search.id.toString())
            }
            val imgPair: Pair<View, String> = Pair.create(imgPoster, imgPoster.transitionName.toString())
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, imgPair).toBundle()
            startActivity(intent, options)
        }
    }
}