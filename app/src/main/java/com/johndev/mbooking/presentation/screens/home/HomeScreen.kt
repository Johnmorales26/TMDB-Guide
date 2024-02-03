package com.johndev.mbooking.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.johndev.mbooking.data.model.Movies
import com.johndev.mbooking.presentation.viewmodels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel, navigation: (Long) -> Unit) {
    val moviesUpcoming: List<Movies> by viewModel.moviesUpcoming.observeAsState(initial = emptyList())
    val moviesNowPlaying: List<Movies> by viewModel.moviesNowPlaying.observeAsState(initial = emptyList())
    val moviesPopular: List<Movies> by viewModel.moviesPopular.observeAsState(initial = emptyList())
    val moviesTopRated: List<Movies> by viewModel.moviesTopRated.observeAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        moviesUpcoming.forEach {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        navigation(it.id)
                    },
                text = it.originalTitle
            )
        }
        Divider()
        moviesNowPlaying.forEach {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        navigation(it.id)
                    },
                text = it.originalTitle
            )
        }
        Divider()
        moviesPopular.forEach {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        navigation(it.id)
                    },
                text = it.originalTitle
            )
        }
        Divider()
        moviesTopRated.forEach {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        navigation(it.id)
                    },
                text = it.originalTitle
            )
        }
    }
}