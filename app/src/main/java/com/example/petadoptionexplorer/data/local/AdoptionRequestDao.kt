
package com.example.petadoptionexplorer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AdoptionRequestDao {

    @Insert
    suspend fun insertRequest(
        request: AdoptionRequestEntity
    ): Long

    @Query("SELECT * FROM adoption_requests ORDER BY submittedAt DESC")
    suspend fun getAllRequests(): List<AdoptionRequestEntity>

}
