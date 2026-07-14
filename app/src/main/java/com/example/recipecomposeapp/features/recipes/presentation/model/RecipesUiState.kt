package com.example.recipecomposeapp.features.recipes.presentation.model

data class RecipesUiState(
    val recipes: List<RecipeUiModel> = emptyList(),
    val categoryTitle: String? = null,
    val categoryImageUrl: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)