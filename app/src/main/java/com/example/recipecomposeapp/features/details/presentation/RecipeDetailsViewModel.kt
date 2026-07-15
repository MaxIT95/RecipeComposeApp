package com.example.recipecomposeapp.features.details.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.recipecomposeapp.core.utils.FavoriteDataStoreManager
import com.example.recipecomposeapp.data.repository.RecipesRepository
import com.example.recipecomposeapp.features.details.presentation.model.RecipeDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(
    application: Application,
    savedStateHandle: SavedStateHandle,
) : AndroidViewModel(application) {

    private val recipesRepository: RecipesRepository = RecipesRepository()
    private val favoriteDataStoreManager: FavoriteDataStoreManager =
        FavoriteDataStoreManager(application)

    private val recipeId: Int = savedStateHandle["recipeId"] ?: 0
    private var isFavorite: Boolean = false
    private val _recipeUiState = MutableStateFlow(RecipeDetailsUiState())
    val recipeUiState = _recipeUiState.asStateFlow()

    init {
        loadRecipe()
        defineIsFavorite()
    }

    fun loadRecipe() {

        _recipeUiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                val recipeModel = recipesRepository.getRecipeById(recipeId)

                if (recipeModel != null) {
                    _recipeUiState.update {
                        it.copy(
                            isLoading = false,
                            recipe = recipeModel
                        )
                    }
                } else {
                    _recipeUiState.update {
                        it.copy(
                            error = "Рецепт не найден!",
                            isLoading = false
                        )
                    }
                }
            } catch (exception: Exception) {
                _recipeUiState.update { it.copy(error = exception.message, isLoading = false) }
            }
        }
    }

    fun onToggleFavorites() {
        viewModelScope.launch {

            if (isFavorite) {
                favoriteDataStoreManager.removeFromFavorites(recipeId)
            } else {
                favoriteDataStoreManager.addFavorite(recipeId)
            }
        }
    }

    private fun defineIsFavorite() {
        viewModelScope.launch {
            val isFavoriteFlow = favoriteDataStoreManager.isFavorite(recipeId)

            isFavoriteFlow.collect { isFav ->
                _recipeUiState.update {
                    it.copy(isFavorite = isFav)
                }
                isFavorite = isFav
            }
        }
    }
}