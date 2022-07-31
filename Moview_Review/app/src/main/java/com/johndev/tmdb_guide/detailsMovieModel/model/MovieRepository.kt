package com.johndev.tmdb_guide.detailsMovieModel.model

import androidx.room.RoomDatabase
import com.johndev.tmdb_guide.common.entities.MoviesEntity
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.common.utils.validateNumberID

class MovieRepository {

    private val roomDatabase = MovieRoomDatabase()

    suspend fun consultMovieByID(id: Int) = roomDatabase.consultMovieByID(id)

    suspend fun saveMovie(moviesEntity: MoviesEntity) {
        if (validateNumberID(moviesEntity.id.toString())) {
            roomDatabase.saveMovie(moviesEntity)
        } else {
            throw Exception(Constans.ERROR_ID)
        }
    }

}