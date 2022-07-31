package com.johndev.tmdb_guide.videosModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.johndev.tmdb_guide.common.entities.MovieDetailsEntity
import com.johndev.tmdb_guide.common.entities.Result
import com.johndev.tmdb_guide.common.entities.Videos
import com.johndev.tmdb_guide.detailsMovieModule.model.DetailsRepository
import com.johndev.tmdb_guide.detailsMovieModule.view.DetailsMovieActivity
import com.johndev.tmdb_guide.detailsMovieModule.viewModel.DetailsViewModel
import com.johndev.tmdb_guide.videosModule.model.VideosRepository
import com.johndev.tmdb_guide.videosModule.view.VideosActivity.Companion.contextVideos

class VideosViewModel : ViewModel() {

    private val repository = VideosRepository(contextVideos)

    fun getResult() = result

    fun getVideosById(id: String) {
        result.value = repository.getVideosById(id)
    }

    companion object {
        val result = MutableLiveData<MutableList<Videos>>()
    }

}