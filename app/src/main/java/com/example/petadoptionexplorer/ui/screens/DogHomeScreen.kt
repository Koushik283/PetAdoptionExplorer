package com.example.petadoptionexplorer.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.petadoptionexplorer.BuildConfig
import com.example.petadoptionexplorer.R
import com.example.petadoptionexplorer.data.remote.RetrofitInstance
import com.example.petadoptionexplorer.model.Dog
import com.example.petadoptionexplorer.ui.components.DogCard
import com.example.petadoptionexplorer.ui.components.DogFilterSection

@Composable
fun DogHomeScreen(
    onDogClick: (Dog) -> Unit,
    onSettingsClick: () -> Unit
) {
    var dogs by remember { mutableStateOf<List<Dog>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var searchText by remember { mutableStateOf("") }
    var selectedBreedGroup by remember { mutableStateOf("All") }
    var selectedOrigin by remember { mutableStateOf("All") }

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

    val allLabel = stringResource(R.string.filter_all)
    val breedGroups = dogs.mapNotNull { it.breed_group?.trim()?.takeIf(String::isNotEmpty) }
        .distinct().sorted()
    val origins = dogs.mapNotNull { it.origin?.trim()?.takeIf(String::isNotEmpty) }
        .distinct().sorted()

    val filteredDogs = dogs.filter { dog ->
        val matchesSearch = dog.name.contains(searchText.trim(), ignoreCase = true)
        val matchesGroup = selectedBreedGroup == allLabel ||
            dog.breed_group.equals(selectedBreedGroup, ignoreCase = true)
        val matchesOrigin = selectedOrigin == allLabel ||
            dog.origin.equals(selectedOrigin, ignoreCase = true)
        matchesSearch && matchesGroup && matchesOrigin
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.pet_adoption_explorer_title),
                    style = MaterialTheme.typography.headlineSmall
                )

                IconButton(onClick = onSettingsClick) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = stringResource(R.string.open_settings)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text(stringResource(R.string.search_dog_breeds)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            DogFilterSection(
                breedGroups = breedGroups,
                selectedBreedGroup = selectedBreedGroup,
                onBreedGroupChanged = { selectedBreedGroup = it },
                origins = origins,
                selectedOrigin = selectedOrigin,
                onOriginChanged = { selectedOrigin = it },
                onClearFilters = {
                    selectedBreedGroup = allLabel
                    selectedOrigin = allLabel
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

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
                        text = stringResource(R.string.unable_to_load_dogs, errorMessage.orEmpty()),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                filteredDogs.isEmpty() -> {
                    Text(
                        text = if (searchText.isBlank() && selectedBreedGroup == allLabel && selectedOrigin == allLabel) {
                            stringResource(R.string.no_dogs_found)
                        } else {
                            stringResource(R.string.search_no_results)
                        }
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredDogs, key = { it.id }) { dog ->
                            DogCard(dog = dog, onClick = { onDogClick(dog) })
                        }
                    }
                }
            }
        }
    }
}
