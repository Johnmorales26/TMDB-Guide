package com.johndev.tmdb_guide.detailsSerieModule.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.adapters.ViewPagerAdapter
import com.johndev.tmdb_guide.databinding.ActivityDetailSeriesBinding
import com.johndev.tmdb_guide.detailsMovieModule.view.DetailsFragment
import com.johndev.tmdb_guide.detailsMovieModule.view.DetailsMovieActivity
import com.johndev.tmdb_guide.detailsMovieModule.view.SugerenciasFragment
import com.johndev.tmdb_guide.detailsMovieModule.viewModel.DetailsViewModel
import com.johndev.tmdb_guide.detailsSerieModule.viewModel.DetailSeriesViewModel
import com.johndev.tmdb_guide.getImage
import com.johndev.tmdb_guide.videosModule.view.VideosActivity
import okhttp3.internal.concurrent.Task

class DetailSeriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSeriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contextDetailsSeries = this
        configureButtons()
        setupViewModel()
        setupObservers()
        configureViewPager()
        getSerie()
    }

    private fun configureButtons() {
        binding.btnReturn.setOnClickListener { finish() }
        binding.btnPlay.setOnClickListener {
            detailsViewModel.getResult().observe(this) {
                val intent = Intent(this, VideosActivity::class.java).apply {
                    putExtra(getString(R.string.key_id_videos_passed), it.id.toString())
                }
                startActivity(intent)
            }
        }
    }

    private fun setupViewModel() {
        detailsViewModel = ViewModelProvider(this)[DetailSeriesViewModel::class.java]
    }

    private fun setupObservers() {
        detailsViewModel.getResult().observe(this) { serie ->
            if (serie == null) {
                binding.tvTitle.text = "No Existe"
            } else {
                binding.let {
                    //  Image
                    Glide
                        .with(this)
                        .load(getImage(serie.backdrop_path.toString().trim()))
                        .centerCrop()
                        .placeholder(R.drawable.ic_cloud_download)
                        .into(it.imgPoster)
                    //  Title
                    it.tvTitle.text = serie.name.toString().trim()
                    //  Overview
                    it.tvOverview.text = serie.overview.toString().trim()
                }
            }
        }
    }

    private fun getSerie() {
        val id = intent.getStringExtra(getString(R.string.key_id_series_passed))
        detailsViewModel.consultSerieByID(id.toString())
    }

    private fun configureViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager).apply {
            add(SugerenceSeriesFragment(), getString(R.string.section_suggestions))
            add(DetailsSeriesFragment(), getString(R.string.section_details))
            add(SeasonsFragment(), getString(R.string.section_seasons))
        }
        binding.viewpager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewpager)
    }

    companion object {
        lateinit var contextDetailsSeries: Context
        lateinit var detailsViewModel: DetailSeriesViewModel
    }

}