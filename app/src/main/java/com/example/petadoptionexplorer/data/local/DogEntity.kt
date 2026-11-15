package com.example.petadoptionexplorer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.petadoptionexplorer.model.Dog
import com.example.petadoptionexplorer.model.DogImage

@Entity(tableName = "dog_favorites")
data class DogEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val bredFor: String? = null,
    val breedGroup: String? = null,
    val lifeSpan: String? = null,
    val temperament: String? = null,
    val origin: String? = null,
    val imageUrl: String? = null
) {
    fun toDog(): Dog = Dog(
        id = id,
        name = name,
        bred_for = bredFor,
        breed_group = breedGroup,
        life_span = lifeSpan,
        temperament = temperament,
        origin = origin,
        image = imageUrl?.let { DogImage(url = it) }
    )

    companion object {
        fun fromDog(dog: Dog): DogEntity = DogEntity(
            id = dog.id,
            name = dog.name,
            bredFor = dog.bred_for,
            breedGroup = dog.breed_group,
            lifeSpan = dog.life_span,
            temperament = dog.temperament,
            origin = dog.origin,
            imageUrl = dog.image?.url
        )
    }
}
