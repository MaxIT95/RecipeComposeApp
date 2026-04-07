package com.example.recipecomposeapp.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

const val RECIPE_APP_PREFS = "recipe_app_prefs"
const val FAVORITES_RECIPE_IDS = "favorite_recipe_ids"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = RECIPE_APP_PREFS,
    produceMigrations = { context ->
        listOf(
            SharedPreferencesMigration(
                context = context,
                sharedPreferencesName = RECIPE_APP_PREFS
            )
        )
    }
)

object PreferencesKeys {
    val FAVORITE_RECIPE_IDS_PREFS = stringSetPreferencesKey(FAVORITES_RECIPE_IDS)
    val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
}

