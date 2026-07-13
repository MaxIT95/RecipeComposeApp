package com.example.recipecomposeapp.features.recipes.presentation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecomposeapp.data.repository.RecipesRepository
import com.example.recipecomposeapp.features.recipes.presentation.model.RecipesUiState
import com.example.recipecomposeapp.features.recipes.presentation.model.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipesViewModel(
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    val recipeRepository = RecipesRepository()

    private val categoryId: Int = savedStateHandle["categoryId"] ?: 0
    private val categoryTitle: String = Uri.decode(savedStateHandle["categoryTitle"] ?: "")
    private val categoryImageUrl: String = Uri.decode(savedStateHandle["categoryImageUrl"] ?: "")
    private val _recipesUiState = MutableStateFlow(RecipesUiState())
    val recipesUiState = _recipesUiState.asStateFlow()

    init {
        _recipesUiState.update {
            it.copy(
                categoryTitle = categoryTitle,
                categoryImageUrl = categoryImageUrl
            )
        }
        loadRecipes()
    }

    fun loadRecipes() {
        _recipesUiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val recipes = recipeRepository.getRecipesByCategoryId(categoryId)
                    .map { it.toUiModel() }

                if (recipes.isNotEmpty()) {
                    _recipesUiState.update { it.copy(recipes = recipes, isLoading = false, error = null) }
                } else {
                    _recipesUiState.update { it.copy(isLoading = false) }
                }
            } catch (exception: Exception) {
                _recipesUiState.update { it.copy(error = exception.message, isLoading = false) }
            }
        }
    }

}