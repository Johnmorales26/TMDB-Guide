package com.johndev.mbooking.presentation.screens.detailsMovie

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.johndev.mbooking.data.model.MovieResponse
import com.johndev.mbooking.data.model.Movies
import com.johndev.mbooking.presentation.viewmodels.DetailsMovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsMovieScreen(viewModel: DetailsMovieViewModel, movieId: Long?) {
    val context = LocalContext.current
    val movie: MovieResponse? by viewModel.movie.observeAsState(initial = null)
    val message: String? by viewModel.message.observeAsState(initial = null)
    if (message != null) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    LaunchedEffect(key1 = Unit) {
        if (movieId != null) {
            viewModel.getMovieDetails(movieId = movieId)
        }
    }

    movie?.let {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(title = { Text(text = it.originalTitle) })
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {

                }
            }
        )
    }
}