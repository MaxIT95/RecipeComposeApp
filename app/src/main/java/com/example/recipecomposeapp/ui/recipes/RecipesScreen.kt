package com.example.recipecomposeapp.ui.recipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipecomposeapp.core.ui.ScreenHeader
import com.example.recipecomposeapp.data.repository.getRecipesByCategoryId
import com.example.recipecomposeapp.ui.categories.model.CategoryUiModel
import com.example.recipecomposeapp.ui.recipes.model.toUiModel
import com.example.recipecomposeapp.ui.theme.Dimensions

@Composable
fun RecipesScreen(category: CategoryUiModel, innerPadding: PaddingValues) {

    Column(
        modifier = Modifier.padding(paddingValues = innerPadding)
    ) {
        ScreenHeader(
            category.title, category.imageUrl
        )
        val receipts = getRecipesByCategoryId(category.id)

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = Dimensions.paddingLarge, vertical = Dimensions.paddingMedium)
        ) {
            items(receipts) {
                ReceiptItem(
                    it.toUiModel(),
                    {})
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun RecipesScreenPreview() {
    RecipesScreen(
        CategoryUiModel(
            1, "Категория",
            "Описание", ""
        ), PaddingValues(0.dp)
    )
}