package com.johndev.tmdb_guide.DetailsMovie

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.size.Scale
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.johndev.tmdb_guide.Constans
import com.johndev.tmdb_guide.Movies.Genre
import com.johndev.tmdb_guide.Movies.Movies
import com.johndev.tmdb_guide.Movies.ProductionCompanies
import com.johndev.tmdb_guide.Movies.SpokenLanguages
import com.johndev.tmdb_guide.Provider.Services.MoviesService
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.databinding.ActivityDetailsMovieBinding
import jp.wasabeef.blurry.Blurry

class DetailsMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsMovieBinding
    private val gson = Gson()
    private lateinit var adapterGenres: GenresMovieAdapter
    private lateinit var adapterLanguajes: SpokenLanguajesAdapter
    private lateinit var adapterCompanies: CompaniesProductionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val idMovie = intent.getStringExtra(getString(R.string.key_movie_passed))
        val url = "movie/$idMovie"
        //  tv/{tv_id}
        val moviesService = MoviesService(this, this, null)

        var urlUp = moviesService.getDetailsMovie(url)
        if (urlUp != null) {
            simpleRequest(urlUp.url)
        }

    }

    fun simpleRequest(url: String) {
        val queue = Volley.newRequestQueue(this)
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            // Display the first 500 characters of the response string.X
            val testModel = gson.fromJson(response, Movies::class.java)
            updateUi(testModel) },
            {
                Toast.makeText(this, "That didn't work!", Toast.LENGTH_SHORT).show()
            }) // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun updateUi(movie: Movies) {
        val imagePosterPath = "${Constans.IMAGES_URL}${movie.poster_path}"
        binding.apply {
            /*imgBackdrop.load(imagePosterPath) {
                crossfade(true)
                scale(Scale.FILL)
                placeholder(R.drawable.ic_broken_image)
            }*/
            Glide.with(this@DetailsMovieActivity)
                .load(imagePosterPath)
                .centerCrop()
                .placeholder(R.drawable.ic_broken_image)
                .into(binding.imgBackdrop)
            imgBackdropVisible.load(imagePosterPath) {
                crossfade(true)
                scale(Scale.FILL)
                placeholder(R.drawable.ic_broken_image)
            }
            tvTitle.text = movie.title.toString().trim()
            tvTagline.text = movie.tagline.toString().trim()
            setupRecyclerViewGenres(movie.genres)
            tvOverview.text = movie.overview.toString().trim()
            val rate = movie.vote_average.toString().trim().replace(".", "")
            tvVoteAverage.text = rate + "%"
            tvVoteCount.text = movie.vote_count.toString().trim()
            configureStarsRate(rate.toFloat())
            setupRecyclerViewLanguajes(movie.spoken_languages)
            setupRecyclerViewCompanies(movie.production_companies)
        }
        configureImg()
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

    private fun setupRecyclerViewLanguajes(langiuajes: List<SpokenLanguages?>?) {
        binding.let {
            adapterLanguajes = SpokenLanguajesAdapter(langiuajes)
            it.rvLanguajes.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailsMovieActivity.adapterLanguajes
            }
        }
    }

    private fun setupRecyclerViewCompanies(companies: List<ProductionCompanies?>?) {
        binding.let {
            adapterCompanies = CompaniesProductionAdapter(companies)
            it.rvCompanies.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailsMovieActivity.adapterCompanies
            }
        }
    }

}