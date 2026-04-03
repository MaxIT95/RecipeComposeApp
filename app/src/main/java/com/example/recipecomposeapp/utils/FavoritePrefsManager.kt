package com.example.recipecomposeapp.utils

import android.content.Context
import androidx.core.content.edit

const val RECIPE_APP_PREFS = "recipe_app_prefs"
const val FAVORITES_RECIPE_IDS = "favorite_recipe_ids"

class FavoritePrefsManager(val context: Context) {

    fun isFavorite(recipeId: Int): Boolean {
        val sharedPreferences =
            context.getSharedPreferences(RECIPE_APP_PREFS, Context.MODE_PRIVATE)
        val favoriteRecipeIds = sharedPreferences.getStringSet(FAVORITES_RECIPE_IDS, emptySet())

        return favoriteRecipeIds?.contains(recipeId.toString()) ?: false
    }

    fun addToFavorites(recipeId: Int) {
        val sharedPreferences =
            context.getSharedPreferences(RECIPE_APP_PREFS, Context.MODE_PRIVATE)
        val favoriteRecipeIds = sharedPreferences.getStringSet(FAVORITES_RECIPE_IDS, emptySet())
        val updatedFavorites = favoriteRecipeIds?.toMutableSet() ?: mutableSetOf()

        updatedFavorites.add(recipeId.toString())

        sharedPreferences.edit {
            putStringSet(FAVORITES_RECIPE_IDS, updatedFavorites)
        }
    }

    fun removeFromFavorites(recipeId: Int) {
        val sharedPreferences =
            context.getSharedPreferences(RECIPE_APP_PREFS, Context.MODE_PRIVATE)
        val favoriteRecipeIds = sharedPreferences.getStringSet(FAVORITES_RECIPE_IDS, emptySet())
        val updatedFavorites = favoriteRecipeIds?.toMutableSet() ?: mutableSetOf()

        updatedFavorites.remove(recipeId.toString())

        sharedPreferences.edit {
            putStringSet(FAVORITES_RECIPE_IDS, updatedFavorites)
        }
    }

    fun getAllFavorites(): Set<String> {
        val sharedPreferences =
            context.getSharedPreferences(RECIPE_APP_PREFS, Context.MODE_PRIVATE)
        return sharedPreferences.getStringSet(FAVORITES_RECIPE_IDS, emptySet()) ?: mutableSetOf()
    }
}