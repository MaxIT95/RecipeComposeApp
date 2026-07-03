package com.example.recipecomposeapp.core.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.recipecomposeapp.core.utils.PreferencesKeys.FAVORITE_RECIPE_IDS_PREFS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.emptySet


class FavoriteDataStoreManager(private val context: Context) {

    fun isFavorite(recipeId: Int): Flow<Boolean> {
        return getFavoriteIdsFlow().map { it.contains(recipeId.toString()) }
    }

    suspend fun addFavorite(recipeId: Int) {
        context.dataStore.edit { preferences ->
            val currentFavorites =
                preferences[FAVORITE_RECIPE_IDS_PREFS] ?: emptySet()
            val updatedFavorites = currentFavorites + recipeId.toString()
            preferences[FAVORITE_RECIPE_IDS_PREFS] = updatedFavorites
        }
    }

    suspend fun removeFromFavorites(recipeId: Int) {
        context.dataStore.edit { preferences ->
            val currentFavorites =
                preferences[FAVORITE_RECIPE_IDS_PREFS] ?: emptySet()
            val updatedFavorites = currentFavorites - recipeId.toString()
            preferences[FAVORITE_RECIPE_IDS_PREFS] = updatedFavorites
        }
    }

    fun getFavoriteIdsFlow(): Flow<Set<String>> {
        return context.dataStore.data.map { preferences ->
            preferences[FAVORITE_RECIPE_IDS_PREFS] ?: emptySet()
        }
    }

    fun getFavoriteCountFlow(): Flow<Int> {
        return getFavoriteIdsFlow().map { it.size }
    }
}