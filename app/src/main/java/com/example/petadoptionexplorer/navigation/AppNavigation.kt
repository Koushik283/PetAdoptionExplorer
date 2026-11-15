
package com.example.petadoptionexplorer.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petadoptionexplorer.data.local.AppDatabase
import com.example.petadoptionexplorer.data.local.DogRepository
import com.example.petadoptionexplorer.model.Dog
import com.example.petadoptionexplorer.model.AdoptionRequest
import com.example.petadoptionexplorer.ui.screens.DogDetailsScreen
import com.example.petadoptionexplorer.ui.screens.DogHomeScreen
import com.example.petadoptionexplorer.ui.screens.AdoptionRequestScreen
import com.example.petadoptionexplorer.ui.screens.FavoritesScreen
import com.example.petadoptionexplorer.ui.screens.SettingsScreen

@Composable
fun AppNavigation(onThemeChanged: (Boolean) -> Unit) {
    val context = LocalContext.current.applicationContext
    val repository = remember {
        DogRepository(AppDatabase.getInstance(context).dogDao())
    }

    val navController = rememberNavController()
    var selectedDog by remember { mutableStateOf<Dog?>(null) }
    var submittedRequest by remember {
        mutableStateOf<AdoptionRequest?>(null)
    }

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            DogHomeScreen(
                repository = repository,
                onDogClick = { dog ->
                    selectedDog = dog
                    navController.navigate("details")
                },
                onFavoritesClick = {
                    navController.navigate("favorites")
                },
                onSettingsClick = {
                    navController.navigate("settings")
                }
            )
        }

        composable("favorites") {
            FavoritesScreen(
                repository = repository,
                onDogClick = { dog ->
                    selectedDog = dog
                    navController.navigate("details")
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("details") {
            selectedDog?.let { dog ->
                DogDetailsScreen(
                    dog = dog,
                    repository = repository,
                    onBack = {
                        navController.popBackStack()
                    },
                    onAdopt = {
                        navController.navigate("adoption")
                    }
                )
            }
        }

        composable("adoption") {
            selectedDog?.let { dog ->
                AdoptionRequestScreen(
                    dogId = dog.id,
                    dogName = dog.name,
                    onBack = {
                        navController.popBackStack()
                    },
                    onSubmit = { request ->
                        submittedRequest = request
                        navController.navigate("confirmation")
                    }
                )
            }
        }

        composable("confirmation") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Request Submitted!",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    "Your adoption request for " +
                            (submittedRequest?.dogName ?: "the dog") +
                            " has been received."
                )

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = {
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text("Back to Home")
                }
            }
        }

        composable("settings") {
            SettingsScreen(
                onBack = {
                    navController.popBackStack()
                },
                onThemeChanged = onThemeChanged
            )
        }
    }
}
