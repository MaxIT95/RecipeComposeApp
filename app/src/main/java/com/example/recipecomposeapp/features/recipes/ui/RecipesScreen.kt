package com.example.recipecomposeapp.features.recipes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipecomposeapp.core.ui.ScreenHeader
import com.example.recipecomposeapp.core.ui.theme.Dimensions
import com.example.recipecomposeapp.core.utils.ASSETS_URI_PREFIX
import com.example.recipecomposeapp.features.recipes.presentation.RecipesViewModel
import com.example.recipecomposeapp.features.recipes.presentation.model.RecipeUiModel

@Composable
fun RecipesScreen(
    onRecipeClick: (Int, RecipeUiModel) -> Unit, innerPadding: PaddingValues,
) {

    val recipesViewModel: RecipesViewModel = viewModel()
    val recipesState by recipesViewModel.recipesUiState.collectAsState()

    Column(
        modifier = Modifier.padding(paddingValues = innerPadding)
    ) {
        ScreenHeader(
            recipesState.categoryTitle!!.uppercase(), recipesState.categoryImageUrl!!
        )

        LazyColumn(
            modifier = Modifier
                .padding(
                    horizontal = Dimensions.paddingLarge,
                    vertical = Dimensions.paddingMedium
                )
        ) {
            items(
                items = recipesState.recipes,
                key = { it.id }) {
                ReceiptItem(
                    it,
                    onRecipeClick
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun RecipesScreenPreview() {
    RecipesScreen(
        { _, _ -> }, PaddingValues(0.dp)
    )
}