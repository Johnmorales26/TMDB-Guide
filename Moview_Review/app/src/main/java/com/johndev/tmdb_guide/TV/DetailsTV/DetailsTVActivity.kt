package com.johndev.tmdb_guide.TV.DetailsTV

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.size.Scale
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.common.adapters.CompaniesProductionAdapter
import com.johndev.tmdb_guide.common.adapters.GenresMovieAdapter
import com.johndev.tmdb_guide.Interfaces.OnPressedSeason
import com.johndev.tmdb_guide.common.entities.Genre
import com.johndev.tmdb_guide.common.entities.ProductionCompanies
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.databinding.ActivityDetailsTvactivityBinding

class DetailsTVActivity : AppCompatActivity(), OnPressedSeason {

    private lateinit var binding: ActivityDetailsTvactivityBinding
    private lateinit var adapterGenres: GenresMovieAdapter
    private lateinit var adapterCompanies: CompaniesProductionAdapter
    private lateinit var adapterSeason: SeasonsAdapter
    private lateinit var adapterLastEpisode: EpisodeAdapter
    private lateinit var adapterNextEpisode: EpisodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsTvactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppBar.setNavigationOnClickListener { finish() }
        val idTV = intent.getStringExtra(getString(R.string.key_tv_passed))
        val url = "tv/${idTV}"
        getDetailsTV(url)
    }

    private fun getDetailsTV(url: String): Request<String>? {
        val queue = Volley.newRequestQueue(this)
        val gson = Gson()
        val url = Constans.URL_BASE + url + Constans.API_KEY_INDEX_SEARCH + Constans.API_KEY
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val testModel = gson.fromJson(response, TvDetails::class.java)
            updateUI(testModel)
        }) {
            Toast.makeText(this, "No such tv exists", Toast.LENGTH_LONG).show()
        }
        return queue.add(stringRequest)
    }

    private fun updateUI(tvModel: TvDetails) {
        val imagePosterPath = "${Constans.IMAGES_URL}${tvModel.poster_path}"
        val imageBackdropPath = "${Constans.IMAGES_URL}${tvModel.backdrop_path}"
        binding.topAppBar.title = tvModel.name.toString().trim()
        //  Upadte Images
        binding.imgBackdrop.load(imageBackdropPath) {
            crossfade(true)
            scale(Scale.FILL)
            placeholder(R.drawable.ic_broken_image)
        }
        binding.imgPost.load(imagePosterPath) {
            crossfade(true)
            scale(Scale.FILL)
            placeholder(R.drawable.ic_broken_image)
        }
        //  Title
        binding.tvTitle.text = tvModel.name.toString().trim()
        //  Tagline
        binding.tvTagline.text = tvModel.tagline.toString().trim()
        //  Genres
        setupRecyclerViewGenres(tvModel.genres)
        //  Companies
        setupRecyclerViewCompanies(tvModel.production_companies as MutableList<ProductionCompanies>)
        //  Overview
        binding.tvOverview.text = tvModel.overview.toString().trim()
        //  Seasons
        setupRecyclerViewSeason(tvModel.seasons as List<Seasons>)
        //  Episodes
        val episodeLast: MutableList<Episode> = mutableListOf()
        tvModel.last_episode_to_air?.let { episodeLast.add(it) }
        val episodeNext: MutableList<Episode> = mutableListOf()
        tvModel.next_episode_to_air?.let { episodeNext.add(it) }
        setupRecyclerViewLastEpisode(episodeLast)
        setupRecyclerViewNextEpisode(episodeNext)
    }

    private fun setupRecyclerViewGenres(genres: List<Genre?>?) {
        binding.let {
            adapterGenres = GenresMovieAdapter(genres)
            it.rvGenres.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailsTVActivity.adapterGenres
            }
        }
    }

    private fun setupRecyclerViewCompanies(companies: MutableList<ProductionCompanies>) {
        binding.let {
            adapterCompanies = CompaniesProductionAdapter(companies)
            it.rvCompanies.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailsTVActivity.adapterCompanies
            }
        }
    }

    private fun setupRecyclerViewSeason(seasons: List<Seasons>) {
        binding.let {
            adapterSeason = SeasonsAdapter(seasons, this)
            it.rvSeasons.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailsTVActivity.adapterSeason
            }
        }
    }

    private fun setupRecyclerViewLastEpisode(episode: List<Episode>) {
        binding.let {
            adapterLastEpisode = EpisodeAdapter(episode)
            it.rvLastEpisode.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailsTVActivity.adapterLastEpisode
            }
        }
    }

    private fun setupRecyclerViewNextEpisode(episode: List<Episode>) {
        binding.let {
            adapterNextEpisode = EpisodeAdapter(episode)
            it.rvNextEpisode.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = this@DetailsTVActivity.adapterNextEpisode
            }
        }
    }

    override fun onSeasonPressed(season: Seasons) {
        Toast.makeText(this, "Season ${season.season_number}", Toast.LENGTH_LONG).show()
    }
}