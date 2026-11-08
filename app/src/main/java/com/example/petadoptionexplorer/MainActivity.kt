
package com.example.petadoptionexplorer

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide
import com.example.petadoptionexplorer.data.remote.RetrofitInstance
import com.example.petadoptionexplorer.model.Dog
import com.example.petadoptionexplorer.ui.theme.PetAdoptionExplorerTheme
import com.example.petadoptionexplorer.BuildConfig

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PetAdoptionExplorerTheme {
                DogHomeScreen()
            }
        }
    }
}

@Composable
fun DogHomeScreen() {

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
                            DogCard(dog)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DogCard(dog: Dog) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            val imageUrl = dog.image?.url

            if (!imageUrl.isNullOrBlank()) {
                val context = LocalContext.current

                AndroidView(
                    factory = {
                        ImageView(it).apply {
                            scaleType = ImageView.ScaleType.CENTER_CROP
                        }
                    },
                    update = { imageView ->
                        Glide.with(imageView)
                            .load(imageUrl)
                            .centerCrop()
                            .into(imageView)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Image not available")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = dog.name,
                style = MaterialTheme.typography.titleLarge
            )

            dog.bred_for?.let {
                Text("Bred for: $it")
            }

            dog.temperament?.let {
                Text("Temperament: $it")
            }

            dog.life_span?.let {
                Text("Life span: $it")
            }
        }
    }
}
