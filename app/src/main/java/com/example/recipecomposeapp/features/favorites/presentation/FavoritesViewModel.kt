package com.example.recipecomposeapp.features.favorites.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecomposeapp.core.utils.FavoriteDataStoreManager
import com.example.recipecomposeapp.data.repository.RecipesRepository
import com.example.recipecomposeapp.features.favorites.presentation.model.FavoritesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    application: Application,
) : AndroidViewModel(application = application) {

    private val favoriteDataStoreManager: FavoriteDataStoreManager =
        FavoriteDataStoreManager(application)
    private val recipeRepository: RecipesRepository = RecipesRepository()
    private val _favoritesUiState = MutableStateFlow(FavoritesUiState())
    val favoritesUiState = _favoritesUiState.asStateFlow()


    init {
        loadFavoriteRecipes()
    }

    fun loadFavoriteRecipes() {
        _favoritesUiState.update { it.copy(error = null, isLoading = true) }

        viewModelScope.launch {

            try {
                favoriteDataStoreManager.getFavoriteIdsFlow().collect { ids ->
                    val recipes = ids.map { id -> recipeRepository.getRecipeById(id.toIntOrNull()) }

                    _favoritesUiState.update { it.copy(isLoading = false, recipes = recipes) }
                }
            } catch (e: Exception) {
                _favoritesUiState.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }
}