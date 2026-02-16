package com.example.recipecomposeapp.data.model

data class Recipe(
    val id: Int, val name: String, val ingredients: List<IngredientDto>,
    val stepsCooking: String,
    val imageUrl: String
)