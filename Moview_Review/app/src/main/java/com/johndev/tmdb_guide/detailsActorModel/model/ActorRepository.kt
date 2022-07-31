package com.johndev.tmdb_guide.detailsActorModel.model

import com.johndev.tmdb_guide.common.entities.ActorEntity
import com.johndev.tmdb_guide.common.utils.Constans.ERROR_ID
import com.johndev.tmdb_guide.common.utils.validateNumberID

class ActorRepository {

    private val actorRoomDatabase = ActorRoomDatabase()

    suspend fun consultActorByID(id: Int) = actorRoomDatabase.consultActorByID(id)

    suspend fun saveActor(actorEntity: ActorEntity) {
        if (validateNumberID(actorEntity.id.toString())) {
            actorRoomDatabase.saveActor(actorEntity)
        } else {
            throw Exception(ERROR_ID)
        }
    }

}