package com.example.petadoptionexplorer.model

data class Dog(
    val id: Int,
    val name: String,
    val bred_for: String? = null,
    val breed_group: String? = null,
    val life_span: String? = null,
    val temperament: String? = null,
    val origin: String? = null,
    val image: DogImage? = null
)

data class DogImage(
    val id: String? = null,
    val url: String? = null
)
