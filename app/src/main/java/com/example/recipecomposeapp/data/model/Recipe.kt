package com.example.recipecomposeapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDto(
    val id: Int,
    val name: String,
    val ingredients: List<IngredientDto>,
    val stepsCooking: String,
    val imageUrl: String
)