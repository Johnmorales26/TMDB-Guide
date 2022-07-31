package com.johndev.tmdb_guide.common.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "ActorEntity", indices = [Index(value = ["id"], unique = true)])
data class ActorEntity(
    var adult: Boolean? = true,
    var biography: String? = null,
    var birthday: String? = null,
    var deathday: String? = null,
    var gender: Int? = null,
    var homepage: String? = null,
    @PrimaryKey var id: Int? = null,
    var imdb_id: String? = null,
    var known_for_department: String? = null,
    var name: String? = null,
    var place_of_birth: String? = null,
    var popularity: Double? = null,
    var profile_path: String? = null
)