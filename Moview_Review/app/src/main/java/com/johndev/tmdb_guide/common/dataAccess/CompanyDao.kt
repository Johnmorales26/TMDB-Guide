package com.johndev.tmdb_guide.common.dataAccess

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.johndev.tmdb_guide.common.entities.CompanyEntity

@Dao
interface CompanyDao {

    @Query("SELECT * FROM CompanyEntity WHERE id = :id")
    suspend fun consultCompanyByID(id: Int): CompanyEntity

    @Insert
    suspend fun addCompany(companyEntity: CompanyEntity): Long

}