package com.example.recipecomposeapp.data.repository

import android.content.Context
import com.example.recipecomposeapp.data.model.CategoryDto
import com.example.recipecomposeapp.data.model.RecipeDto

import kotlinx.serialization.json.Json

class RecipesRepositoryStub(private val context: Context) {

    private val categoryList = loadCategoriesFromJson()

    private val burgerRecipes = loadRecipesFromJson()

    private val json = Json { ignoreUnknownKeys = true }

    private fun loadCategoriesFromJson(): List<CategoryDto> {
        val jsonCategory =
            context.assets.open("category.json").bufferedReader().use { it.readText() }
        return json.decodeFromString(jsonCategory)
    }

    private fun loadRecipesFromJson(): List<RecipeDto> {
        val jsonCategory =
            context.assets.open("recipe.json").bufferedReader().use { it.readText() }
        return json.decodeFromString(jsonCategory)
    }

    fun getCategories(): List<CategoryDto> {
        return categoryList
    }

    fun getRecipesByCategoryId(categoryId: Int): List<RecipeDto> {
        return when (categoryId) {
            0 -> burgerRecipes  // Имитация GET /category/0/recipes
            else -> emptyList() // Остальные категории пока пустые
        }
    }
}