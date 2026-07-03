package com.example.recipecomposeapp.features.categories.presentation.model

import androidx.compose.runtime.Immutable
import com.example.recipecomposeapp.core.utils.ASSETS_URI_PREFIX
import com.example.recipecomposeapp.data.model.CategoryDto

@Immutable
data class CategoryUiModel(
    val id: Int = 0, val title: String = "",
    val description: String = "", val imageUrl: String = ""
)

fun CategoryDto.toUiModel() = CategoryUiModel(
    id = id,
    title = title,
    description = description,
    imageUrl = if (imageUrl.startsWith("http")) imageUrl else ASSETS_URI_PREFIX + imageUrl
)
