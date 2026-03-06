package com.example.recipecomposeapp.ui.categories.model

import androidx.compose.runtime.Immutable
import com.example.recipecomposeapp.data.model.CategoryDto
import com.example.recipecomposeapp.utils.ASSETS_URI_PREFIX

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
