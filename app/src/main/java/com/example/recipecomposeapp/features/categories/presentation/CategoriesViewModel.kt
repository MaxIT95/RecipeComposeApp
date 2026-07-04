package com.example.recipecomposeapp.features.categories.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipecomposeapp.data.repository.RecipesRepository
import com.example.recipecomposeapp.features.categories.presentation.model.CategoriesUiState
import com.example.recipecomposeapp.features.categories.presentation.model.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel: ViewModel() {

    val recipesRepository: RecipesRepository = RecipesRepository()

    private val _categoriesUiState = MutableStateFlow(CategoriesUiState())
    val categoriesUiState = _categoriesUiState.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories() {

        viewModelScope.launch {
            try {
                _categoriesUiState.update { it.copy(isLoading = true, error = null) }

                val categoryList = recipesRepository.getCategories().map { it.toUiModel() }
                _categoriesUiState.update { it.copy(categories = categoryList, isLoading = false) }
            } catch (exception: Exception) {
                Log.e(
                    "ERROR",
                    "Произошла ошибка при первоначальной загрузке категорий рецептов: ${exception.message}"
                )
                _categoriesUiState.update { it.copy(isLoading = false, error = exception.message) }
            }
        }
    }
}