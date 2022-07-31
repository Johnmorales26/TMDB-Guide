package com.johndev.tmdb_guide.common.dataAccess

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.johndev.tmdb_guide.common.entities.MoviesEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM MoviesEntity WHERE id = :id")
    suspend fun consultMovieByID(id: Int): MoviesEntity

    @Insert
    suspend fun addMovie(moviesEntity: MoviesEntity): Long

}