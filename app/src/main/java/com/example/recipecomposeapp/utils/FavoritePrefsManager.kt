package com.example.recipecomposeapp.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first


class FavoriteDataStoreManager(private val context: Context) {

    suspend fun isFavorite(recipeId: Int): Boolean {
        val preferences = context.dataStore.data.first()
        val favoriteIds = preferences[PreferencesKeys.FAVORITE_RECIPE_IDS_PREFS] ?: emptySet()
        return favoriteIds.contains(recipeId.toString())
    }

    suspend fun addFavorite(recipeId: Int) {
        context.dataStore.edit { preferences ->
            val currentFavorites =
                preferences[PreferencesKeys.FAVORITE_RECIPE_IDS_PREFS] ?: emptySet()
            val updatedFavorites = currentFavorites + recipeId.toString()
            preferences[PreferencesKeys.FAVORITE_RECIPE_IDS_PREFS] = updatedFavorites
        }
    }

   suspend fun removeFromFavorites(recipeId: Int) {
       context.dataStore.edit { preferences ->
           val currentFavorites =
               preferences[PreferencesKeys.FAVORITE_RECIPE_IDS_PREFS] ?: emptySet()
           val updatedFavorites = currentFavorites - recipeId.toString()
           preferences[PreferencesKeys.FAVORITE_RECIPE_IDS_PREFS] = updatedFavorites
       }
    }

   suspend fun getAllFavorites(): Set<String> {
        val preferences = context.dataStore.data.first()
       return preferences[PreferencesKeys.FAVORITE_RECIPE_IDS_PREFS] ?: emptySet()
    }
}