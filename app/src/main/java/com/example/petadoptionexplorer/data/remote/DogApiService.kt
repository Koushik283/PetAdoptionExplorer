package com.example.petadoptionexplorer.data.remote

import com.example.petadoptionexplorer.model.Dog
import retrofit2.http.GET
import retrofit2.http.Header

interface DogApiService {

    @GET("breeds")
    suspend fun getBreeds(
        @Header("x-api-key") apiKey: String
    ): List<Dog>
}
