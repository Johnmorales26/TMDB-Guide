package com.johndev.tmdb_guide.common.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "CompanyEntity", indices = [Index(value = ["id"], unique = true)])
data class CompanyEntity(
    var description: String? = "",
    var headquarters: String? = "",
    var homepage: String? = "",
    @PrimaryKey var id: Int? = 0,
    var logo_path: String? = "",
    var name: String? = "",
    var origin_country: String? = "",
    var parent_company: String? = ""
)