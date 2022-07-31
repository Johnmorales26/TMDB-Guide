package com.johndev.tmdb_guide.detailsMovieModel.model

import com.johndev.tmdb_guide.common.dataAccess.MovieApplication
import com.johndev.tmdb_guide.common.dataAccess.MovieDao
import com.johndev.tmdb_guide.common.entities.MoviesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MovieRoomDatabase {

    private val dao: MovieDao by lazy { MovieApplication.database.movieDao() }

    suspend fun consultMovieByID(id: Int) = dao.consultMovieByID(id)

    suspend fun saveMovie(moviesEntity: MoviesEntity) = withContext(Dispatchers.IO) {
        try {
            dao.addMovie(moviesEntity)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

}