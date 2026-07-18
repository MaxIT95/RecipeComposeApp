package com.example.recipecomposeapp.features.favorites.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipecomposeapp.core.ui.ScreenHeader
import com.example.recipecomposeapp.core.ui.theme.Dimensions
import com.example.recipecomposeapp.core.ui.theme.RecipesAppTypography
import com.example.recipecomposeapp.core.utils.ASSETS_URI_PREFIX
import com.example.recipecomposeapp.features.favorites.presentation.FavoritesViewModel
import com.example.recipecomposeapp.features.recipes.presentation.model.RecipeUiModel
import com.example.recipecomposeapp.features.recipes.ui.ReceiptItem

const val FAVORITES_IMAGE_URL = ASSETS_URI_PREFIX + "bcg_favorites.png"

@Composable
fun FavoritesScreen(
    innerPadding: PaddingValues,
    onRecipeClick: (Int, RecipeUiModel) -> Unit
) {
    val viewModel: FavoritesViewModel = viewModel()

    val favoritesState by viewModel.favoritesUiState.collectAsState()

    Column(modifier = Modifier.padding(innerPadding)) {
        ScreenHeader("ИЗБРАННОЕ", FAVORITES_IMAGE_URL)
        Spacer(Modifier.padding(vertical = 10.dp))

        if (favoritesState.isLoading) {
            CircularProgressIndicator()
        } else if (favoritesState.error != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Ошибка при загрузке избранного: ${favoritesState.error}")
                Spacer(Modifier.padding(vertical = 15.dp))

                Button(
                    onClick = { viewModel.loadFavoriteRecipes() },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Повторить")
                }
            }
        } else if (favoritesState.recipes.isEmpty()) {
            Text(
                text = "Список избранных рецептов пуст",
                color = MaterialTheme.colorScheme.primary,
                style = RecipesAppTypography.displayLarge,
                textAlign = TextAlign.Center
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = Dimensions.paddingLarge)
            ) {
                items(
                    items = favoritesState.recipes,
                    key = { it.id }) {
                    ReceiptItem(
                        it,
                        onRecipeClick
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun FavoritesScreenPreview() {
    FavoritesScreen(
        PaddingValues(Dimensions.paddingLarge),
        { _, _ -> }
    )
}