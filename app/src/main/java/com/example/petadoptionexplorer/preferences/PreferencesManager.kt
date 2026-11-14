package com.example.petadoptionexplorer.preferences

import android.content.Context
import androidx.core.content.edit

class PreferencesManager(context: Context) {
    private val preferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)

    fun isDarkThemeEnabled(): Boolean = preferences.getBoolean(KEY_DARK_THEME, false)

    fun setDarkThemeEnabled(enabled: Boolean) {
        preferences.edit { putBoolean(KEY_DARK_THEME, enabled) }
    }

    companion object {
        private const val PREFERENCES_FILE = "pet_adoption_explorer_preferences"
        private const val KEY_DARK_THEME = "dark_theme_enabled"
    }
}
