package com.johndev.tmdb_guide.common.dataAccess

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.johndev.tmdb_guide.common.entities.DataRequestEntity
import java.time.chrono.MinguoDate

@Dao
interface DataRequestDao {

    @Query("SELECT * FROM DataRequestEntity WHERE id = :id")
    suspend fun consultDataRequestByID(id: Int): DataRequestEntity

    @Insert
    suspend fun addDataRequest(dataRequest: DataRequestEntity): Long

}