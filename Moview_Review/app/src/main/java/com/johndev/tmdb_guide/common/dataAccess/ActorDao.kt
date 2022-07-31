package com.johndev.tmdb_guide.common.dataAccess

import androidx.room.Dao
import androidx.room.Index
import androidx.room.Insert
import androidx.room.Query
import com.johndev.tmdb_guide.common.entities.ActorEntity

@Dao
interface ActorDao {

    @Query("SELECT * FROM ActorEntity WHERE id = :id")
    suspend fun consultActorById(id: Int): ActorEntity?

    @Insert
    suspend fun addActor(actorEntity: ActorEntity): Long

}