package com.johndev.tmdb_guide.MoviesTheaters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.johndev.tmdb_guide.common.entities.KnownFor
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.Interfaces.OnPressedMovie
import com.johndev.tmdb_guide.common.adapters.CardMovieAdapter
import com.johndev.tmdb_guide.common.entities.MoviePopular
import com.johndev.tmdb_guide.Provider.Services.MoviesService
import com.johndev.tmdb_guide.common.entities.DataRequestEntity
import com.johndev.tmdb_guide.databinding.FragmentMoviesTheatersBinding
import com.johndev.tmdb_guide.mainModel.view.MainActivity.Companion.mainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesTheatersFragment : Fragment(), OnPressedMovie {

    private var _binding: FragmentMoviesTheatersBinding? = null
    private val binding get() = _binding!!
    private var moviesList: MutableList<MoviePopular> = mutableListOf()
    private lateinit var adapter: CardMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesTheatersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        lifecycleScope.launch(Dispatchers.Default) {
            inflateList()
        }
    }

    private suspend fun inflateList() = withContext(Dispatchers.Default) {
        val moviesService = MoviesService(activity, requireContext(), adapter)
        lifecycleScope.launch(Dispatchers.Default) {
            //moviesService.getDataMovie(Constans.API_POPULAR_MOVIES, mainViewModel)
        }
    }

    private fun setupRecyclerView() {
        val data: MutableList<DataRequestEntity> = mutableListOf()
        binding.let {
            adapter = CardMovieAdapter(data, this)
            it.recyclerView.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = this@MoviesTheatersFragment.adapter
            }
        }
    }

    override fun OnMoviePressed(movie: DataRequestEntity, imgPhoto: View) {

    }

    override fun OnMovieKnowPressed(movie: KnownFor) {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}