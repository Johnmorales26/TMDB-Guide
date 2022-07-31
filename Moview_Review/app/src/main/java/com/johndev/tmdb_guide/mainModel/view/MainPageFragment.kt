package com.johndev.tmdb_guide.mainModel.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.johndev.tmdb_guide.common.entities.KnownFor
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.detailsMovieModel.view.DetailsMovieActivity
import com.johndev.tmdb_guide.Interfaces.OnPressedMovie
import com.johndev.tmdb_guide.common.adapters.CardMovieAdapter
import com.johndev.tmdb_guide.R
import android.util.Pair
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.entities.DataRequestEntity
import com.johndev.tmdb_guide.common.entities.PagesPopularMovies
import com.johndev.tmdb_guide.common.utils.Constans.API_CURRENT_MOVIES
import com.johndev.tmdb_guide.common.utils.Constans.API_POPULAR_MOVIES
import com.johndev.tmdb_guide.common.utils.Constans.API_TOM_CRUISE_MOVIES
import com.johndev.tmdb_guide.common.utils.Constans.API_YEAR_DRAMA
import com.johndev.tmdb_guide.databinding.FragmentMainPageBinding
import com.johndev.tmdb_guide.mainModel.view.MainActivity.Companion.mainViewModel
import com.johndev.tmdb_guide.mainModel.viewModel.MainViewModel
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
        configureAdapters()
        lifecycleScope.launch(Dispatchers.Default) {
            inflateList()
        }
        setupObserver()
    }

    private fun configureAdapters() {
        val dataList: MutableList<DataRequestEntity> = mutableListOf()
        adapter = CardMovieAdapter(dataList, this)
        adapterCurrentCinema = CardMovieAdapter(dataList, this)
        adapterCurrentDrama = CardMovieAdapter(dataList, this)
        adapterTomsMovies = CardMovieAdapter(dataList, this)
        setupRecyclerView(adapter, binding.recyclerView)
        setupRecyclerView(adapterCurrentCinema, binding.rvPlayingCinema)
        setupRecyclerView(adapterCurrentDrama, binding.rvDramaYear)
        setupRecyclerView(adapterTomsMovies, binding.rvTomsMovies)
    }

    private fun setupRecyclerView(myAdapter: CardMovieAdapter, recyclerView: RecyclerView) {
        activity?.let {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = myAdapter
            }
        }
    }

    private fun setupObserver() {
        activity?.let {
            mainViewModel.getResultPopular().observe(it) { movies ->
                adapter.add(movies)
            }
            mainViewModel.getResultCurrent().observe(it) { movies ->
                adapterCurrentCinema.add(movies)
            }
            mainViewModel.getResultDrama().observe(it) { movies ->
                adapterCurrentDrama.add(movies)
            }
            mainViewModel.getResultBest().observe(it) { movies ->
                adapterTomsMovies.add(movies)
            }
        }
    }

    private suspend fun inflateList() = withContext(Dispatchers.Default) {
        lifecycleScope.launch(Dispatchers.Default) {
            getDataMovie(API_POPULAR_MOVIES, mainViewModel)
            getDataMovie(API_CURRENT_MOVIES, mainViewModel)
            getDataMovie(API_YEAR_DRAMA, mainViewModel)
            getDataMovie(API_TOM_CRUISE_MOVIES, mainViewModel)
        }
    }

    suspend fun getDataMovie(typeUrl: String, mainViewModel: MainViewModel) = withContext(Dispatchers.IO) {
        val queue = Volley.newRequestQueue(activity)
        val gson = Gson()
        val request: MutableList<DataRequestEntity> = mutableListOf()
        val url = Constans.URL_BASE + typeUrl + Constans.API_KEY_INDEX + Constans.API_KEY
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val testModel = gson.fromJson(response, PagesPopularMovies::class.java)
            testModel.results?.forEach { movie ->
                if (movie != null) {
                    val dataRequestEntity = DataRequestEntity(movie.id!!.toLong(), movie.poster_path.toString(),
                        movie.title.toString(), Constans.TYPE_MOVIE
                    )
                    when(typeUrl) {
                        API_POPULAR_MOVIES -> {
                            mainViewModel.saveDataPopular(dataRequestEntity)
                            MainActivity.mainViewModel.consultDataPopularByID(dataRequestEntity.id.toString().trim().toInt())
                        }
                        API_CURRENT_MOVIES -> {
                            mainViewModel.saveDataCurent(dataRequestEntity)
                            MainActivity.mainViewModel.consultDataCurrentByID(dataRequestEntity.id.toString().trim().toInt())
                        }
                        API_YEAR_DRAMA -> {
                            mainViewModel.saveDataDrama(dataRequestEntity)
                            MainActivity.mainViewModel.consultDataDramaByID(dataRequestEntity.id.toString().trim().toInt())
                        }
                        API_TOM_CRUISE_MOVIES -> {
                            mainViewModel.saveDataBest(dataRequestEntity)
                            MainActivity.mainViewModel.consultDataBestByID(dataRequestEntity.id.toString().trim().toInt())
                        }
                    }
                }
            }
        }) {
            Toast.makeText(context, "No such movies exists", Toast.LENGTH_LONG).show()
        }
        queue.add(stringRequest)
        return@withContext request
    }

    override fun OnMoviePressed(movie: DataRequestEntity, imgPhoto: View) {
        val intent = Intent(context, DetailsMovieActivity::class.java).apply {
            putExtra(getString(R.string.key_movie_passed), movie.id.toString().trim())
        }
        val imgPair: Pair<View, String> = Pair.create(imgPhoto, getString(R.string.tn_imgMovie))
        val options = ActivityOptions.makeSceneTransitionAnimation(activity, imgPair)
            .toBundle()
        startActivity(intent, options)
    }

    override fun OnMovieKnowPressed(movie: KnownFor) {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}