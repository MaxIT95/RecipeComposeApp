package com.example.recipecomposeapp.ui.categories.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipecomposeapp.core.ui.ScreenHeader
import com.example.recipecomposeapp.data.repository.getCategories
import com.example.recipecomposeapp.ui.categories.CategoryItem
import com.example.recipecomposeapp.ui.categories.model.CategoryUiModel
import com.example.recipecomposeapp.ui.categories.model.toUiModel
import com.example.recipecomposeapp.ui.theme.Dimensions
import com.example.recipecomposeapp.utils.ASSETS_URI_PREFIX

const val CATEGORIES_IMAGE_URL = ASSETS_URI_PREFIX + "bcg_categories.png"

@Composable
fun CategoriesScreen(innerPadding: PaddingValues, onCategoryClick: (CategoryUiModel) -> Unit) {
    Column(modifier = Modifier.padding(innerPadding)) {
        ScreenHeader("КАТЕГОРИИ", CATEGORIES_IMAGE_URL)
        Column {
            val categories = getCategories().map { it.toUiModel() }
            CategoryCards(categories, onCategoryClick)
        }
    }
}

@Composable
fun CategoryCards(categories: List<CategoryUiModel>, onCategoryClick: (CategoryUiModel) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .padding(horizontal = Dimensions.paddingLarge)
            .padding(top = Dimensions.paddingLarge, bottom = Dimensions.paddingSmall),
        horizontalArrangement = Arrangement.spacedBy(Dimensions.paddingLarge),
        verticalArrangement = Arrangement.spacedBy(Dimensions.paddingLarge),
        content = {
            items(categories) { category ->
                CategoryItem(
                    category, onCategoryClick
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun CategoriesScreenPreview() {
    CategoriesScreen(PaddingValues(Dimensions.paddingLarge), {})
}