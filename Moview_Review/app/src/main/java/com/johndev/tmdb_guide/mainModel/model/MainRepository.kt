package com.johndev.tmdb_guide.mainModel.model

import com.johndev.tmdb_guide.common.entities.DataRequestEntity
import com.johndev.tmdb_guide.common.utils.Constans
import com.johndev.tmdb_guide.common.utils.validateNumberID

class MainRepository {

    private val roomDatabase = MainRoomDatabase()

    suspend fun consultDataRequestByID(id: Int) = roomDatabase.consutDataRquestByID(id)

    suspend fun saveDataRequest(dataRequestEntity: DataRequestEntity) {
        if (validateNumberID(dataRequestEntity.id.toString().trim())) {
            roomDatabase.saveDataRequest(dataRequestEntity)
        } else {
            throw Exception(Constans.ERROR_ID)
        }
    }

}