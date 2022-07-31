package com.johndev.tmdb_guide.detailsCompanyModel.model

import com.johndev.tmdb_guide.common.dataAccess.CompanyDao
import com.johndev.tmdb_guide.common.dataAccess.MovieApplication
import com.johndev.tmdb_guide.common.entities.CompanyEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CompanyRoomDatabase {

    private val dao: CompanyDao by lazy { MovieApplication.database.companyDao() }

    suspend fun consultCompanyByID(id: Int) = dao.consultCompanyByID(id)

    suspend fun addCompany(companyEntity: CompanyEntity) = withContext(Dispatchers.IO) {
        try {
            dao.addCompany(companyEntity)
        } catch (e: Exception){
            throw Exception(e.message)
        }
    }

}