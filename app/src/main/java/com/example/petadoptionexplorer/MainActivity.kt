package com.example.petadoptionexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.petadoptionexplorer.navigation.AppNavigation
import com.example.petadoptionexplorer.preferences.PreferencesManager
import com.example.petadoptionexplorer.ui.theme.PetAdoptionExplorerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val preferences = PreferencesManager(applicationContext)
        var darkThemeEnabled by mutableStateOf(preferences.isDarkThemeEnabled())

        setContent {
            PetAdoptionExplorerTheme(darkTheme = darkThemeEnabled) {
                AppNavigation(onThemeChanged = { enabled -> darkThemeEnabled = enabled })
            }
        }
    }
}
