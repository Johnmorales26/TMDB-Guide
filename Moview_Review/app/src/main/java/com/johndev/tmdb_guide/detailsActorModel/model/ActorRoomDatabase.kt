package com.johndev.tmdb_guide.detailsActorModel.model

import com.johndev.tmdb_guide.common.dataAccess.ActorDao
import com.johndev.tmdb_guide.common.dataAccess.MovieApplication
import com.johndev.tmdb_guide.common.entities.ActorEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ActorRoomDatabase {

    private val dao: ActorDao by lazy { MovieApplication.database.actorDao() }

    suspend fun consultActorByID(id: Int) = dao.consultActorById(id)

    suspend fun saveActor(actorEntity: ActorEntity) = withContext(Dispatchers.IO) {
        try {
            dao.addActor(actorEntity)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

}