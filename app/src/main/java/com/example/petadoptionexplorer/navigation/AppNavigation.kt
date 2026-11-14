package com.example.petadoptionexplorer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petadoptionexplorer.model.Dog
import com.example.petadoptionexplorer.ui.screens.DogDetailsScreen
import com.example.petadoptionexplorer.ui.screens.DogHomeScreen
import com.example.petadoptionexplorer.ui.screens.SettingsScreen

@Composable
fun AppNavigation(onThemeChanged: (Boolean) -> Unit) {
    val navController = rememberNavController()
    var selectedDog by remember { mutableStateOf<Dog?>(null) }

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            DogHomeScreen(
                onDogClick = { dog ->
                    selectedDog = dog
                    navController.navigate("details")
                },
                onSettingsClick = { navController.navigate("settings") }
            )
        }

        composable("details") {
            selectedDog?.let { dog ->
                DogDetailsScreen(
                    dog = dog,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable("settings") {
            SettingsScreen(
                onBack = { navController.popBackStack() },
                onThemeChanged = onThemeChanged
            )
        }
    }
}
