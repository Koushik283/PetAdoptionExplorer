
package com.example.petadoptionexplorer.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.petadoptionexplorer.model.Dog
import com.example.petadoptionexplorer.ui.screens.DogDetailsScreen
import com.example.petadoptionexplorer.ui.screens.DogHomeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    var selectedDog by remember {
        mutableStateOf<Dog?>(null)
    }

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            DogHomeScreen(
                onDogClick = { dog ->
                    selectedDog = dog
                    navController.navigate("details")
                }
            )
        }

        composable("details") {
            selectedDog?.let { dog ->
                DogDetailsScreen(
                    dog = dog,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
