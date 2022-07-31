package com.johndev.tmdb_guide.common.dataAccess

import androidx.room.Database
import androidx.room.RoomDatabase
import com.johndev.tmdb_guide.common.entities.ActorEntity
import com.johndev.tmdb_guide.common.entities.CompanyEntity
import com.johndev.tmdb_guide.common.entities.DataRequestEntity
import com.johndev.tmdb_guide.common.entities.MoviesEntity

@Database(entities = [MoviesEntity::class, ActorEntity::class, CompanyEntity::class, DataRequestEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
    abstract fun companyDao(): CompanyDao
    abstract fun dataRequestDao(): DataRequestDao

}