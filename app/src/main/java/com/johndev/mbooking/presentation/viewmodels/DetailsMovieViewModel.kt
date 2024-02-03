package com.johndev.mbooking.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johndev.mbooking.R
import com.johndev.mbooking.data.model.MovieResponse
import com.johndev.mbooking.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class DetailsMovieViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: MoviesRepository
) : ViewModel() {

    private val _movie = MutableLiveData<MovieResponse>()
    val movie: LiveData<MovieResponse> = _movie

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    suspend fun getMovieDetails(movieId: Long) {
        viewModelScope.launch {
            try {
                val response = repository.getMovieDetails(movieId = movieId, lang = "es-MX")
                _movie.value = response
            } catch (e: HttpException) {
                // Manejar respuestas HTTP no exitosas aquí
                when (e.code()) {
                    404 -> {
                        _message.value = context.getString(R.string.not_found_error_message_404)
                    }
                    // Agrega más casos según sea necesario para manejar otros códigos de error.
                    else -> {
                        _message.value = context.getString(R.string.error_server, e.code())
                    }
                }
            } catch (e: Exception) {
                _message.value = context.getString(R.string.error_unknown, e.message)
            }
        }
    }

}