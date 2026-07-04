package com.example.recipecomposeapp.features.recipes.presentation.model

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.example.recipecomposeapp.core.utils.ASSETS_URI_PREFIX
import com.example.recipecomposeapp.data.model.RecipeDto
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class RecipeUiModel(
    val id: Int, val title: String,
    val ingredients: List<IngredientUiModel>,
    val method: List<String>,
    val imageUrl: String,
    val portions: Int = 1
) : Parcelable

fun RecipeDto.toUiModel() = RecipeUiModel(
    id = id,
    title = title,
    ingredients = ingredients.map { it.toUiModel() },
    method = method,
    imageUrl = if (imageUrl.startsWith("http")) imageUrl else ASSETS_URI_PREFIX + imageUrl
)