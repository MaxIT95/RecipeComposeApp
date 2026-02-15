package com.example.recipecomposeapp.model

data class Recipe(
    val id: Int, val name: String, val ingredients: List<Ingredient>,
    val stepsCooking: String,
    val imageUrl: String
)