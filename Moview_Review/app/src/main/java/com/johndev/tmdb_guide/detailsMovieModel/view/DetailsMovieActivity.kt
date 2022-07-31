package com.johndev.tmdb_guide.detailsMovieModel.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.size.Scale
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.adapters.CompaniesProductionAdapter
import com.johndev.tmdb_guide.common.adapters.GenresMovieAdapter
import com.johndev.tmdb_guide.common.adapters.SpokenLanguajesAdapter
import com.johndev.tmdb_guide.common.entities.*
import com.johndev.tmdb_guide.common.utils.*
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY
import com.johndev.tmdb_guide.common.utils.Constans.API_KEY_INDEX_SEARCH
import com.johndev.tmdb_guide.common.utils.Constans.API_MOVIE_DETAILS
import com.johndev.tmdb_guide.common.utils.Constans.URL_BASE
import com.johndev.tmdb_guide.databinding.ActivityDetailsMovieBinding
import com.johndev.tmdb_guide.detailsMovieModel.viewModel.MovieViewModel
import jp.wasabeef.blurry.Blurry

class DetailsMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsMovieBinding
    private lateinit var movieViewModel: MovieViewModel
    private val gson = Gson()
    private lateinit var adapterGenres: GenresMovieAdapter
    private lateinit var adapterLanguajes: SpokenLanguajesAdapter
    private lateinit var adapterCompanies: CompaniesProductionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupObservers()
        recibeMovie()
        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun recibeMovie() {
        val idMovie = intent.getStringExtra(getString(R.string.key_movie_passed))
        getDetailsMovieFromAPI(idMovie.toString().trim())
        movieViewModel.consultMovieByID(idMovie.toString().toInt())
    }

    private fun setupObservers() {
        movieViewModel.getResult().observe(this) { movie ->
            if (movie == null) {
                binding.tvTitle.text = "Pelicula No Disponible"
            } else {
                binding.topAppBar.title = movie.title.toString().trim()
                binding.apply {
                    Glide.with(this@DetailsMovieActivity)
                        .load(getImageResource(movie.poster_path.toString()))
                        .centerCrop()
                        .placeholder(R.drawable.ic_broken_image)
                        .into(binding.imgBackdrop)
                    imgBackdropVisible.load(getImageResource(movie.poster_path.toString())) {
                        crossfade(true)
                        scale(Scale.FILL)
                        placeholder(R.drawable.ic_broken_image)
                    }
                    tvTitle.text = movie.title.toString().trim()
                    tvTagline.text = movie.tagline.toString().trim()
                    //  Setup RecyclerView Genres
                    val genre = getGenresList(movie.genres.toString().trim())
                    setupRecyclerViewGenres(genre)
                    tvOverview.text = movie.overview.toString().trim()
                    val rate = movie.vote_average.toString().trim().replace(".", "")
                    tvVoteAverage.text = rate + "%"
                    tvVoteCount.text = movie.vote_count.toString().trim()
                    configureStarsRate(rate.toFloat())
                    val spoken_languages = getSpokenLanguajesList(movie.spoken_languages.toString().trim())
                    setupRecyclerViewLanguajes(spoken_languages)
                    val product_companies = getProductionCompaniesList(movie.production_companies.toString().trim())
                    setupRecyclerViewCompanies(product_companies)
                }
                configureImg()
            }
        }
    }

    private fun setupViewModel() {
        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
    }

    fun getDetailsMovieFromAPI(url: String) {
        val queue = Volley.newRequestQueue(this)
        val url = URL_BASE + API_MOVIE_DETAILS + url + API_KEY_INDEX_SEARCH + API_KEY
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val movieModel = gson.fromJson(response, MoviesAPI::class.java)
            val moviesEntity = transformMAtoME(movieModel)
            movieViewModel.saveMovie(moviesEntity)
        }) {
            Toast.makeText(this, "No such movies exists", Toast.LENGTH_LONG).show()
        }
        queue.add(stringRequest)
    }

    private fun configureStarsRate(rate: Float) {
        val imageList: List<ImageView> = listOf(binding.imgRate1, binding.imgRate2, binding.imgRate3, binding.imgRate4, binding.imgRate5)
        if (rate <= 10) {
            val starList: List<Int> = listOf(R.drawable.ic_star_half, R.drawable.ic_star_outline, R.drawable.ic_star_outline, R.drawable.ic_star_outline, R.drawable.ic_star_outline)
            asignPhoto(imageList, starList)
        } else if (rate <= 20) {
            val starList: List<Int> = listOf(R.drawable.ic_star, R.drawable.ic_star_outline, R.drawable.ic_star_outline, R.drawable.ic_star_outline, R.drawable.ic_star_outline)
            asignPhoto(imageList, starList)
        } else if (rate <= 30) {
            val starList: List<Int> = listOf(R.drawable.ic_star, R.drawable.ic_star_half, R.drawable.ic_star_outline, R.drawable.ic_star_outline, R.drawable.ic_star_outline)
            asignPhoto(imageList, starList)
        } else if (rate <= 40) {
            val starList: List<Int> = listOf(R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star_outline, R.drawable.ic_star_outline, R.drawable.ic_star_outline)
            asignPhoto(imageList, starList)
        } else if (rate <= 50) {
            val starList: List<Int> = listOf(R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star_half, R.drawable.ic_star_outline, R.drawable.ic_star_outline)
            asignPhoto(imageList, starList)
        } else if (rate <= 60) {
            val starList: List<Int> = listOf(R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star_outline, R.drawable.ic_star_outline)
            asignPhoto(imageList, starList)
        } else if (rate <= 70) {
            val starList: List<Int> = listOf(R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star_half, R.drawable.ic_star_outline)
            asignPhoto(imageList, starList)
        } else if (rate <= 80) {
            val starList: List<Int> = listOf(R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star_outline)
            asignPhoto(imageList, starList)
        } else if (rate <= 90) {
            val starList: List<Int> = listOf(R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star_half)
            asignPhoto(imageList, starList)
        } else if (rate <= 100) {
            val starList: List<Int> = listOf(R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star)
            asignPhoto(imageList, starList)
        }
    }

    private fun asignPhoto(imgRate: List<ImageView>, startRating: List<Int>) {
        var x = 0
        while(x < 5) {
            Glide
                .with(this)
                .load(startRating[x])
                .centerCrop()
                .placeholder(R.drawable.ic_broken_image)
                .into(imgRate[x])
            x++
        }
    }

    private fun configureImg() {
        Blurry.with(this)
            .sampling(3)
            .color(Color.argb(80, 72, 72, 72))
            .async()
            .animate(500)
            .capture(binding.imgBackdrop)
            .into(binding.imgBackdrop)
    }

    private fun setupRecyclerViewGenres(genres: List<Genre?>?) {
        binding.let {
            adapterGenres = GenresMovieAdapter(genres)
            it.rvGenres.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailsMovieActivity.adapterGenres
            }
        }
    }

    private fun setupRecyclerViewLanguajes(langiuajes: MutableList<SpokenLanguages>) {
        binding.let {
            adapterLanguajes = SpokenLanguajesAdapter(langiuajes)
            it.rvLanguajes.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailsMovieActivity.adapterLanguajes
            }
        }
    }

    private fun setupRecyclerViewCompanies(companies: MutableList<ProductionCompanies>) {
        binding.let {
            adapterCompanies = CompaniesProductionAdapter(companies)
            it.rvCompanies.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailsMovieActivity.adapterCompanies
            }
        }
    }

}