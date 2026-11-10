
package com.example.petadoptionexplorer.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.petadoptionexplorer.BuildConfig
import com.example.petadoptionexplorer.data.remote.RetrofitInstance
import com.example.petadoptionexplorer.model.Dog
import com.example.petadoptionexplorer.ui.components.DogCard

@Composable
fun DogHomeScreen(onDogClick: (Dog) -> Unit) {

    var dogs by remember { mutableStateOf<List<Dog>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            isLoading = true
            errorMessage = null
            dogs = RetrofitInstance.api.getBreeds(BuildConfig.DOG_API_KEY)
        } catch (e: Exception) {
            errorMessage = e.message ?: "Something went wrong"
        } finally {
            isLoading = false
        }
    }

    val filteredDogs = dogs.filter {
        it.name.contains(searchText, ignoreCase = true)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Pet Adoption Explorer 🐾",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Search dog breeds") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                errorMessage != null -> {
                    Text(
                        text = "Unable to load dogs: $errorMessage",
                        color = MaterialTheme.colorScheme.error
                    )
                }

                filteredDogs.isEmpty() -> {
                    Text("No dog breeds found.")
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredDogs) { dog ->
                            DogCard(
                                dog = dog,
                                onClick = { onDogClick(dog) }
                            )
                        }
                    }
                }
            }
        }
    }
}
