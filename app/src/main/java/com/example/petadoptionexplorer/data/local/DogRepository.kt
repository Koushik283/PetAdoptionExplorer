package com.example.petadoptionexplorer.data.local

import com.example.petadoptionexplorer.model.Dog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DogRepository(private val dogDao: DogDao) {

    fun observeFavoriteDogs(): Flow<List<Dog>> =
        dogDao.getFavoriteDogs().map { dogs -> dogs.map { it.toDog() } }

    fun observeFavoriteIds(): Flow<Set<Int>> =
        dogDao.getFavoriteIds().map { ids -> ids.toSet() }

    suspend fun addFavorite(dog: Dog) {
        dogDao.insertDog(DogEntity.fromDog(dog))
    }

    suspend fun removeFavorite(dogId: Int) {
        dogDao.deleteById(dogId)
    }

    suspend fun isFavorite(dogId: Int): Boolean = dogDao.isFavorite(dogId)

    suspend fun toggleFavorite(dog: Dog) {
        if (dogDao.isFavorite(dog.id)) {
            dogDao.deleteById(dog.id)
        } else {
            dogDao.insertDog(DogEntity.fromDog(dog))
        }
    }
}
