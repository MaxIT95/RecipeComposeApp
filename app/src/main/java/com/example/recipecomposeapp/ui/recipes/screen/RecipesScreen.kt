package com.example.recipecomposeapp.ui.recipes.screen

import android.util.Log
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
import com.example.recipecomposeapp.data.repository.RecipesRepository
import com.example.recipecomposeapp.ui.recipes.ReceiptItem
import com.example.recipecomposeapp.ui.recipes.model.RecipeUiModel
import com.example.recipecomposeapp.ui.recipes.model.toUiModel
import com.example.recipecomposeapp.ui.theme.Dimensions
import com.example.recipecomposeapp.utils.ASSETS_URI_PREFIX

@Composable
fun RecipesScreen(
    onRecipeClick: (Int, RecipeUiModel) -> Unit,
    categoryId: Int, innerPadding: PaddingValues,
    repository: RecipesRepository
) {
    // получаем объект категории по categoryId
    val category = repository.getCategoryById(categoryId)

    if (category != null) {

        var receipts by remember { mutableStateOf<List<RecipeDto>>(emptyList()) }

        Column(
            modifier = Modifier.padding(paddingValues = innerPadding)
        ) {
            ScreenHeader(
                category.title.uppercase(), ASSETS_URI_PREFIX + category.imageUrl
            )

            LaunchedEffect(categoryId) {
                receipts = repository.getRecipesByCategoryId(categoryId)
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
                        onRecipeClick
                    )
                }
            }
        }
    } else {
        Log.e("error", "Объект категории не найден")
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun RecipesScreenPreview() {
    RecipesScreen(
        { _, _ -> }, 1, PaddingValues(0.dp),
        RecipesRepository()
    )
}