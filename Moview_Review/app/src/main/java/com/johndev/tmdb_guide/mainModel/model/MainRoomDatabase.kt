package com.johndev.tmdb_guide.mainModel.model

import com.johndev.tmdb_guide.common.dataAccess.DataRequestDao
import com.johndev.tmdb_guide.common.dataAccess.MovieApplication
import com.johndev.tmdb_guide.common.entities.DataRequestEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainRoomDatabase {

    private val dao: DataRequestDao by lazy { MovieApplication.database.dataRequestDao() }

    suspend fun consutDataRquestByID(id: Int) = dao.consultDataRequestByID(id)

    suspend fun saveDataRequest(dataRequestEntity: DataRequestEntity) = withContext(Dispatchers.IO) {
        try {
            dao.addDataRequest(dataRequestEntity)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

}