package com.example.recipecomposeapp.features.favorites.presentation.model

import com.example.recipecomposeapp.features.recipes.presentation.model.RecipeUiModel

data class FavoritesUiState(
    val recipes: List<RecipeUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)