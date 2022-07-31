package com.johndev.tmdb_guide.detailsMovieModule.view

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.adapters.ViewPagerAdapter
import com.johndev.tmdb_guide.databinding.ActivityDetailsMovieBinding
import com.johndev.tmdb_guide.detailsMovieModule.viewModel.DetailsViewModel
import com.johndev.tmdb_guide.detailsSerieModule.view.SugerenceSeriesFragment
import com.johndev.tmdb_guide.getImage
import com.johndev.tmdb_guide.videosModule.view.VideosActivity


class DetailsMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contextDetails = this
        setupViewModel()
        setupObservers()
        configureViewPager()
        configureButtons()
        getMovie()
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

    private fun getMovie() {
        val id = intent.getStringExtra(getString(R.string.key_id_passed))
        detailsViewModel.consultMovieByID(id.toString().trim())
    }

    private fun setupViewModel() {
        detailsViewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
    }

    private fun setupObservers() {
        detailsViewModel.getResult().observe(this) { movie ->
            if (movie == null) {
                binding.tvTitle.text = getString(R.string.msj_does_not_exit)
            } else {
                binding.let {
                    //  Image
                    Glide
                        .with(this)
                        .load(getImage(movie.backdrop_path.toString().trim()))
                        .centerCrop()
                        .placeholder(R.drawable.ic_cloud_download)
                        .into(it.imgPoster)
                    //  Title
                    it.tvTitle.text = movie.title.toString().trim()
                    //  Overview
                    it.tvOverview.text = movie.overview.toString().trim()
                }
            }
        }
    }

    private fun configureViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager).apply {
            add(SugerenciasFragment(), getString(R.string.section_suggestions))
            add(DetailsFragment(), getString(R.string.section_details))
        }
        binding.viewpager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewpager)
    }

    companion object {
        lateinit var contextDetails: Context
        lateinit var detailsViewModel: DetailsViewModel
    }

}