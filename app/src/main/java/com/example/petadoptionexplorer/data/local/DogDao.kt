package com.example.petadoptionexplorer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dog: DogEntity)

    @Query("DELETE FROM dog_favorites WHERE id = :dogId")
    suspend fun deleteById(dogId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM dog_favorites WHERE id = :dogId)")
    suspend fun isFavorite(dogId: Int): Boolean

    @Query("SELECT * FROM dog_favorites ORDER BY name ASC")
    fun getFavoriteDogs(): Flow<List<DogEntity>>

    @Query("SELECT id FROM dog_favorites ORDER BY name ASC")
    fun getFavoriteIds(): Flow<List<Int>>
}
