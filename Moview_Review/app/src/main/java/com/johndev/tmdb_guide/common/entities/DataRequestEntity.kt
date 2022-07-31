package com.johndev.tmdb_guide.common.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "DataRequestEntity", indices = [Index(value = ["id"], unique = true)])
data class DataRequestEntity (
    @PrimaryKey var id: Long = 0,
    var backdrop_path: String = "",
    var name: String = "",
    var type: String = ""
)
