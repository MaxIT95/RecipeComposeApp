package com.example.recipecomposeapp.ui.recipes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipecomposeapp.R
import com.example.recipecomposeapp.core.ui.ScreenHeader
import com.example.recipecomposeapp.ui.favorites.screen.ReceiptCard

@Composable
fun RecipesScreen(innerPadding: PaddingValues) {
    Column(modifier = Modifier.padding(paddingValues = innerPadding)) {
        ScreenHeader("Рецепты ", R.drawable.bcg_favorites)

        LazyColumn {
            item {
                ReceiptCard(
                    "Скоро здесь будет список рецептов",
                    R.drawable.burger_hamburger
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun RecipesScreenPreview() {

}