package com.johndev.tmdb_guide.videosModule.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.johndev.tmdb_guide.OnVideoListener
import com.johndev.tmdb_guide.R
import com.johndev.tmdb_guide.common.adapters.VideoAdapter
import com.johndev.tmdb_guide.common.entities.Videos
import com.johndev.tmdb_guide.databinding.ActivityVideosBinding
import com.johndev.tmdb_guide.videosModule.viewModel.VideosViewModel

class VideosActivity : AppCompatActivity(), OnVideoListener {

    private lateinit var binding: ActivityVideosBinding
    private lateinit var videosAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureAdapters()
        contextVideos = this
        setupViewModel()
        setupObservers()
        getVideos()
    }

    private fun getVideos() {
        val id = intent.getStringExtra(getString(R.string.key_id_videos_passed))
        videosViewModel.getVideosById(id.toString().trim())
    }

    private fun configureAdapters(videoList: MutableList<Videos> = mutableListOf()) {
        videosAdapter = VideoAdapter(videoList, this)
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = videosAdapter
        }
    }

    private fun setupViewModel() {
        videosViewModel = ViewModelProvider(this)[VideosViewModel::class.java]
    }

    private fun setupObservers() {
        videosViewModel.getResult().observe(this) { videos ->
            if (videos == null) {
                binding.tvLoadVideo.text = getString(R.string.tv_does_not_exist_video)
            } else {
                binding.let {
                    it.containerLoading.visibility = GONE
                    it.recyclerview.visibility = VISIBLE
                    configureAdapters(videos)
                }
            }
        }
    }

    override fun onClick(video: Videos) {
        val watchVideo = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + video.key))
        startActivity(watchVideo)
    }

    companion object {
        lateinit var contextVideos: Context
        lateinit var videosViewModel: VideosViewModel
    }

}