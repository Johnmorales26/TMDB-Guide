package com.johndev.tmdb_guide.detailsCompanyModel.model

import androidx.room.RoomDatabase
import com.johndev.tmdb_guide.common.entities.CompanyEntity
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.common.utils.validateNumberID

class CompanyRepository {

    private val roomDatabase = CompanyRoomDatabase()

    suspend fun consultCompanyByID(id: Int) = roomDatabase.consultCompanyByID(id)

    suspend fun saveCompany(companyEntity: CompanyEntity) {
        if (validateNumberID(companyEntity.id.toString().trim())) {
            roomDatabase.addCompany(companyEntity)
        } else {
            throw Exception(Constans.ERROR_ID)
        }
    }

}