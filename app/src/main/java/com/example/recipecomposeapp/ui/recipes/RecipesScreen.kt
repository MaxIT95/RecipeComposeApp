package com.example.recipecomposeapp.ui.recipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recipecomposeapp.core.ui.ScreenHeader
import com.example.recipecomposeapp.data.model.RecipeDto
import com.example.recipecomposeapp.data.repository.getRecipesByCategoryId
import com.example.recipecomposeapp.ui.recipes.model.toUiModel
import com.example.recipecomposeapp.ui.theme.Dimensions

@Composable
fun RecipesScreen(
    onRecipeClick: (Int) -> Unit,
    categoryId: Int, categoryImageUrl: String, categoryTitle: String, innerPadding: PaddingValues
) {
    var receipts by remember { mutableStateOf<List<RecipeDto>>(emptyList()) }

    Column(
        modifier = Modifier.padding(paddingValues = innerPadding)
    ) {
        ScreenHeader(
            categoryTitle, categoryImageUrl
        )

        LaunchedEffect(Unit) {
            receipts = getRecipesByCategoryId(categoryId)
        }

        LazyColumn(
            modifier = Modifier
                .padding(
                    horizontal = Dimensions.paddingLarge,
                    vertical = Dimensions.paddingMedium
                )
        ) {
            items(
                items = receipts,
                key = { it.id }) {
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
        {}, 1, "Категория",
        "Описание", PaddingValues(0.dp)
    )
}