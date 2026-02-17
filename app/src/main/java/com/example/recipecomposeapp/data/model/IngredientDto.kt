package com.example.recipecomposeapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class IngredientDto(val count: Int, val unitOfChange: String, val description: String)