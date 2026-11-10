
package com.example.petadoptionexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.petadoptionexplorer.navigation.AppNavigation
import com.example.petadoptionexplorer.ui.theme.PetAdoptionExplorerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            PetAdoptionExplorerTheme {
                AppNavigation()
            }
        }
    }
}
